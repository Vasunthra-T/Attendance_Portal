package com.quinbay.email.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.quinbay.email.api.EmailService;
import com.quinbay.email.api.SummaryService;
import com.quinbay.email.model.EmailDetails;
import com.quinbay.email.model.TimesheetApproval;
import com.quinbay.email.repository.TimesheetApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class SummaryServiceImpl implements SummaryService {

    @Autowired
    private EmailService emailService;

    @Autowired
    TimesheetApprovalRepository timesheetApprovalRepository;


    Document document = new Document();
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    @Override
    public String splitByEmployee(LocalDate toDate){
        List<LocalDate> weekDates = Arrays.asList(DayOfWeek.values()).stream().map(toDate::with).collect(toList());
        LocalDate fromDate = LocalDate.now();

        for (LocalDate date : weekDates) {
            if (date.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
                fromDate = date;
            }
        }
        List<TimesheetApproval> email =  fetchFromTimesheet("E101",fromDate, toDate);

        generatePdf(email);

        return "Mail sent successfully";
    }

    @Override
    public List<TimesheetApproval> fetchFromTimesheet(String empCode, LocalDate fromDate, LocalDate toDate) {
            List<TimesheetApproval> filterTimesheet = timesheetApprovalRepository.findByEmpCodeAndWorkingDateBetween(empCode, fromDate, toDate);
            return filterTimesheet;
    }

    @Override
    public String generatePdf(List<TimesheetApproval> t){
        String file_name = "/Users/vasunthra/Desktop/Attendance_Portal/" + t.get(0).getEmpCode()+ ".pdf";
        try {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            addMetaData(document);
            addContent(document, t);
            System.out.println("Your employee code is " + t.get(0).getEmpCode());
            System.out.println("Weekly timesheet can be found at " + file_name);
            document.close();
            EmailDetails ed = new EmailDetails();
            ed.setRecipient("vasunthrat@gmail.com");
            ed.setSubject("Weekly timesheet");
            ed.setMsgBody("Hello \n\n You can find your weekly timesheet here");
            ed.setAttachment(file_name);

            emailService.sendMailWithAttachment(ed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "PDF generated";
    }

    private static void addMetaData(Document document) {
        document.addTitle("Timesheet");
        document.addSubject("For weekly basis");
        document.addKeywords("Hours, InType, Status");
        document.addAuthor(" Vasunthra");
        document.addCreator("Vasunthra");
    }

    private static void addContent(Document document, List<TimesheetApproval> t) throws DocumentException {

        Chapter catPart = new Chapter(new Paragraph(t.get(0).getEmpCode()+ " Timesheet"), 1);

        Paragraph p = new Paragraph();
        LocalDate date = LocalDate.now();
        p.add(new Paragraph("Timesheet date: " + date, smallBold));
        p.setAlignment(Element.ALIGN_RIGHT);
        catPart.addAll(p);

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 2);
        catPart.addAll(paragraph);



        createTable(catPart, t);

        Paragraph gap = new Paragraph();
        addEmptyLine(gap, 3);
        catPart.addAll(gap);


        document.add(catPart);
    }

    private static void createTable(Section subCatPart, List<TimesheetApproval> t) throws BadElementException {

        double sub_total = 0;
        double total = 0;

        PdfPTable table = new PdfPTable(6);

        PdfPCell c1 = new PdfPCell(new Phrase("Emp Code"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Emp Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Working Date"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Productive Hours"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("In Type"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Status"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for (TimesheetApproval approval : t) {
            table.addCell(approval.getEmpCode());
            table.addCell(approval.getEmpName());
            table.addCell(approval.getWorkingDate().toString());
            table.addCell(approval.getProductiveHours().toString());
            table.addCell(approval.getInType().toString());
            table.addCell(approval.getStatus().toString());
        }


        subCatPart.add(table);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
