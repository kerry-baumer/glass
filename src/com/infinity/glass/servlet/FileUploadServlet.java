package com.infinity.glass.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.infinity.glass.manager.DatasetManager;
import com.infinity.glass.manager.ManagerFactory;
import com.infinity.glass.model.DatasetBean;
import com.infinity.glass.model.DatasetSummaryBean;
import com.infinity.glass.model.UserIdentity;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/fileupload")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserIdentity userIdentity = ManagerFactory.getUserIdentityManager().getUserIdentity(request);
			StringBuilder sb = new StringBuilder("{\"result\": [");
			OutputStream uploadedData = new ByteArrayOutputStream();
			String fileName = "N/A";
			int fileSize = 0;
			if (request.getHeader("Content-Type") != null
					&& request.getHeader("Content-Type").startsWith("multipart/form-data")) {
				ServletFileUpload upload = new ServletFileUpload();

				FileItemIterator iterator = upload.getItemIterator(request);
				while (iterator.hasNext()) {
					sb.append("{");
					FileItemStream item = iterator.next();
					sb.append("\"fieldName\":\"").append(item.getFieldName()).append("\",");
					if (item.getName() != null) {
						fileName = item.getName();
						fileSize = size(item.openStream(), uploadedData);
						sb.append("\"name\":\"").append(fileName).append("\",");
						sb.append("\"size\":\"").append(fileSize).append("\"");
					} else {
						sb.append("\"value\":\"").append(read(item.openStream())).append("\"");
					}
					sb.append("}");
					if (iterator.hasNext()) {
						sb.append(",");
					}
				}
			} else {
				sb.append("{\"size\":\"" + size(request.getInputStream(), uploadedData) + "\"}");
			}

			sb.append("]");
			sb.append(", \"requestHeaders\": {");
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String header = headerNames.nextElement();
				sb.append("\"").append(header).append("\":\"").append(request.getHeader(header)).append("\"");
				if (headerNames.hasMoreElements()) {
					sb.append(",\n");
				}
			}
			sb.append("}}");
			LOGGER.info("Glass uploaded file: " + sb.toString());
			DatasetManager dsm = ManagerFactory.getDatasetManager();
			DatasetSummaryBean summ = dsm.getDatasetsForUser(userIdentity);
			DatasetBean dsb = dsm.SaveDataset(userIdentity, fileName, uploadedData);
			summ.addPersonalData(dsb);
			Gson gson = new GsonBuilder()
				.setVersion(1.0)
				.create();
			final String respText = gson.toJson(summ);
			response.getWriter().write(respText);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}		
	}

	protected int size(InputStream stream, OutputStream out) {
		int length = 0;
		try {
			byte[] buffer = new byte[2048];
			int size;
			while ((size = stream.read(buffer)) != -1) {
				length += size;
				out.write(buffer);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return length;

	}

	protected String read(InputStream stream) {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
			}
		}
		return sb.toString();

	}
	
}
