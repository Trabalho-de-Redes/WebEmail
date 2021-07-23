package Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import util.Email;

@WebServlet("/EnviarMailAnexadoServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)      // 50MB
public class ControllerEmail extends HttpServlet {

    /**
     * handles form submission
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        List<File> uploadedFiles = saveUploadedFiles(request);

        String userEmail = request.getParameter("email");
        String senha = request.getParameter("senha");
        String to = request.getParameter("to");
        String cc = request.getParameter("cc");
        String bcc = request.getParameter("bcc");
        String subject = request.getParameter("subject");
        String mensagem = request.getParameter("mensagem");
        String mailServer = request.getParameter("mailServer");

        String resultMessage = "";

        try {

            Boolean result = Email.enviarEmail(mailServer, userEmail, to, cc, bcc, subject, mensagem, senha, uploadedFiles);

            if (result) {
                resultMessage = "O E-mail foi enviado com sucesso!";
            } else {
                resultMessage = "Ocorreu um erro no envio";
            }

        } catch (Exception ex) {
            resultMessage = "Error " + ex.getMessage();
        } finally {
            deleteUploadFiles(uploadedFiles);
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/resultado.jsp").forward(
                    request, response);
        }
    }

    private List<File> saveUploadedFiles(HttpServletRequest request)
            throws IllegalStateException, IOException, ServletException {
        List<File> listFiles = new ArrayList<>();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        Collection<Part> multiparts = request.getParts();
        if (multiparts.size() > 0) {
            for (Part part : request.getParts()) {
                // cria um arquivo a ser salvo
                String fileName = extractFileName(part);
                if (fileName == null || fileName.equals("")) {
                    // não é parte do anexo, continue
                    continue;
                }

                File saveFile = new File(fileName);
                System.out.println("saveFile: " + saveFile.getAbsolutePath());
                FileOutputStream outputStream = new FileOutputStream(saveFile);

                // salva o arquivo enviado
                InputStream inputStream = part.getInputStream();
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();

                listFiles.add(saveFile);
            }
        }
        return listFiles;
    }

      //Recupera o nome do arquivo de uma parte de upload de seu cabeçalho HTTP
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return null;
    }

    // Exclui todos os arquivos carregados, deve ser chamado após o envio do e-mail.
    private void deleteUploadFiles(List<File> listFiles) {
        if (listFiles != null && listFiles.size() > 0) {
            for (File aFile : listFiles) {
                aFile.delete();
            }
        }
    }
}
