package com.example.SpringMVC.view;

import com.example.SpringMVC.model.Material;
import org.springframework.web.servlet.View;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class DownloadingView implements View {
    private final Material material;

    public DownloadingView(Material material) {
        this.material = material;
    }


    @Override
    public String getContentType() {
        return this.material.getMimeContentType();
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=" + this.material.getMaterialName());
        response.setContentType("application/octet-stream");

        ServletOutputStream stream = response.getOutputStream();
        stream.write(this.material.getContents());
    }
}
