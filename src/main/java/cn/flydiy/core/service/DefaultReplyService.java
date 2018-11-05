//package cn.flydiy.core.service;
//
//import cn.flydiy.core.dao.ReplyRepository;
//import cn.flydiy.core.entity.Discussion;
//import cn.flydiy.core.entity.Reply;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class DefaultReplyService implements ReplyService
//{
//    @Autowired
//    ReplyRepository replyRepository;
//    @Autowired DiscussionService discussionService;
//    @Autowired NotificationService notificationService;
//
//    @Override
//    public List<Reply> getRepliesForDiscussion(long discussionId)
//    {
//        List<Reply> list = this.replyRepository.getForDiscussion(discussionId);
//        list.sort((r1, r2) -> r1.getId() < r2.getId() ? -1 : 1);
//        return list;
//    }
//
//    @Override
//    public void saveReply(Reply reply)
//    {
//        Discussion discussion =
//                this.discussionService.getDiscussion(reply.getDiscussionId());
//        if(reply.getId() < 1)
//        {
//            discussion.getSubscribedUsers().add(reply.getUser());
//            reply.setCreated(Instant.now());
//            this.replyRepository.add(reply);
//
//            Set<String> recipients = new HashSet<>(discussion.getSubscribedUsers());
//            recipients.remove(reply.getUser()); // no need to email replier
//            this.notificationService.sendNotification(
//                    "Reply posted", "Someone replied to \"" + discussion.getSubject()
//                    + ".\"", recipients
//            );
//        }
//        else
//        {
//            this.replyRepository.update(reply);
//        }
//        this.discussionService.saveDiscussion(discussion);
//    }
//}