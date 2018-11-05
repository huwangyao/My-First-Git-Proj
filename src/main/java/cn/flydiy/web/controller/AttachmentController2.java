//package cn.flydiy.core.controller;
//
//import cn.flydiy.core.annotation.WebController;
//import cn.flydiy.core.dto.AttachmentDto;
//import cn.flydiy.core.entity.Attachment;
//import cn.flydiy.core.validation.NotBlank;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.View;
//import org.springframework.web.servlet.view.RedirectView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.validation.ConstraintViolationException;
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.security.Principal;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by flying on 16-11-26.
// */
//@WebController
//public class AttachmentController extends BaseController{
//
//    private static final Logger log = LogManager.getLogger();
//
//    @RequestMapping(value = "create", method = RequestMethod.POST)
//    public ModelAndView create(Principal principal, @Valid TicketForm form,
//                               Errors errors, Map<String, Object> model)
//            throws IOException
//    {
//        if(errors.hasErrors())
//            return new ModelAndView("ticket/add");
//
//        Ticket ticket = new Ticket();
//        ticket.setCustomerName(principal.getName());
//        ticket.setSubject(form.getSubject());
//        ticket.setBody(form.getBody());
//
//        for(MultipartFile filePart : form.getAttachments())
//        {
//            log.debug("Processing attachment for new ticket.");
//            Attachment attachment = new Attachment();
//            attachment.setName(filePart.getOriginalFilename());
//            attachment.setMimeContentType(filePart.getContentType());
//            attachment.setContents(filePart.getBytes());
//            if((attachment.getName() != null && attachment.getName().length() > 0) ||
//                    (attachment.getContents() != null && attachment.getContents().length > 0))
//                ticket.addAttachment(attachment);
//        }
//
//        try
//        {
//            this.ticketService.save(ticket);
//        }
//        catch (ConstraintViolationException e)
//        {
//            model.put("validationErrors", e.getConstraintViolations());
//            return new ModelAndView("ticket/add");
//        }
//
//        return new ModelAndView(new RedirectView(
//                "/ticket/view/" + ticket.getId(), true, false
//        ));
//    }
//
//
//    public void upload(HttpSession session, AttachmentDto attachmentDto) throws IOException
//    {
//
//
//
//
//        for(MultipartFile filePart : attachmentDto.getAttachments()) {
//            log.debug("Processing attachment for new ticket.");
//            Attachment attachment = new Attachment();
//            attachment.setName(filePart.getOriginalFilename());
//            attachment.setMimeContentType(filePart.getContentType());
//            attachment.setContents(filePart.getBytes());
//            if ((attachment.getName() != null && attachment.getName().length() > 0) ||
//                    (attachment.getContents() != null && attachment.getContents().length > 0)){
////                ticket.addAttachment(attachment);
//            }
//
//        }
//
//
//
//    }
//
//    @RequestMapping(
//            value = "/{ticketId}/attachment/{attachment:.+}",
//            method = RequestMethod.GET
//    )
//    public View download(@PathVariable("ticketId") long ticketId,
//                         @PathVariable("attachment") String name)
//    {
//        Ticket ticket = this.ticketService.getTicket(ticketId);
//        if(ticket == null)
//            return this.getListRedirectView();
//
//        Attachment attachment = ticket.getAttachment(name);
//        if(attachment == null)
//        {
//            log.info("Requested attachment {} not found on ticket {}.", name, ticket);
//            return this.getListRedirectView();
//        }
//
//        return new DownloadingView(attachment.getName(),
//                attachment.getMimeContentType(), attachment.getContents());
//    }
//
//    public void download(HttpServletRequest req, HttpServletResponse resp) throws IOException
//    {
//
//        File file = new File("");
//
//        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
//        byte[] buffer = new byte[fis.available()];
//        fis.read(buffer);
//
//        resp.setCharacterEncoding("utf8");
//        resp.setHeader("Content-Disposition", "attachment;filename=" + new String( file.getName().getBytes("gb2312"), "ISO8859-1" ));
//        resp.setContentType("application/octet-stream");
////
////        resp.reset();
//        OutputStream toClient = new BufferedOutputStream(resp.getOutputStream());
//        toClient.write(buffer);
//        toClient.flush();
//        toClient.close();
//
//
//    }
//
//    public static class TicketForm
//    {
//        @NotBlank(message = "{validate.ticket.subject}")
//        private String subject;
//        @NotBlank(message = "{validate.ticket.body}")
//        private String body;
//        @NotNull(message = "{validate.ticket.attachments}")
//        private List<MultipartFile> attachments;
//
//        public String getSubject()
//        {
//            return subject;
//        }
//
//        public void setSubject(String subject)
//        {
//            this.subject = subject;
//        }
//
//        public String getBody()
//        {
//            return body;
//        }
//
//        public void setBody(String body)
//        {
//            this.body = body;
//        }
//
//        public List<MultipartFile> getAttachments()
//        {
//            return attachments;
//        }
//
//        public void setAttachments(List<MultipartFile> attachments)
//        {
//            this.attachments = attachments;
//        }
//    }
//
//}
