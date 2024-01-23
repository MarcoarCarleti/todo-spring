package br.com.marcocarleti.todoapplication.tasks.services;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcocarleti.todoapplication.tasks.entities.Task;
import br.com.marcocarleti.todoapplication.tasks.repositories.TaskRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioService {

    @Autowired
    private TaskRepository taskRepository;

    // Outros métodos e lógica existente aqui...

    public byte[] gerarRelatorio() {
        try {
            // Carregar o arquivo JRXML
            InputStream inputStream = getClass().getResourceAsStream("/relatorio_de_tasks.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // Obter dados do banco de dados (você pode usar o seu repositório Task)
            List<Task> tasks = taskRepository.findAll();

            // Preencher o relatório com os dados
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tasks);
            Map<String, Object> params = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            // Exportar o relatório para um array de bytes (PDF)
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            // Lidar com exceções
            e.printStackTrace();
            return null;
        }
    }

    public byte[] generatePdf(List<Task> tasks) {
        try {
            // Carregar o arquivo JRXML
            InputStream inputStream = getClass().getResourceAsStream("/relatorio_de_tasks.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // Preencher o relatório com a lista de tarefas fornecida
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tasks);
            Map<String, Object> params = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            // Exportar o relatório para um array de bytes (PDF)
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            // Lidar com exceções
            e.printStackTrace();
            return null;
        }
    }
}


