package org.flylib.mall.shop.service;

import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.flylib.mall.common.constant.ResponseCode;
import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.common.dto.TableData;
import org.flylib.mall.shop.entity.Topic;
import org.flylib.mall.shop.model.PageParam;
import org.flylib.mall.shop.repository.TopicRepository;
import org.flylib.mall.shop.util.ObjectConverter;
import org.flylib.mall.shop.vo.TopicMobileVO;
import org.flylib.mall.shop.vo.TopicVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TopicService {
    private static final Logger log = LoggerFactory.getLogger(TopicService.class);

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    private ResponseData doSave(Topic topic) {
        ResponseData responseData = new ResponseData();
        /**
         * responseCode = 2;系统错误
         */
        int responseCode = 2;
        String responseMsg = "系统错误";

        if (topic != null && topic.getTopicId() > 0) {
            long topicId = topic.getTopicId();
            Topic existTopic = topicRepository.findById(topicId);
            Date nowTime = new Date();
            if (existTopic == null) {
                topic.setCreateTime(nowTime);
                topic.setUpdateTime(nowTime);
                int updatedCount = topicRepository.add(topic);
                if (updatedCount == 1) {
                    responseCode = ResponseCode.OK;
                    responseMsg = "操作成功";
                } else {
                    responseCode = 3;
                    responseMsg = "插入数据库失败";
                }
            } else {
                log.info("ThisTopicExists");
                topic.setCreateTime(existTopic.getCreateTime());
                topic.setUpdateTime(nowTime);
                int updatedCount = topicRepository.update(topic);
                if (updatedCount == 1) {
                    responseCode = ResponseCode.OK;
                    responseMsg = "操作成功";
                } else {
                    responseCode = 4;
                    responseMsg = "更新数据库失败";
                }
            }
        }

        responseData.setCode(responseCode);
        responseData.setMsg(responseMsg);
        return responseData;
    }

    public ResponseData save(Topic topic) {
        if (topic == null) {
            ResponseData responseData = new ResponseData();
            responseData.setCode(1);
            responseData.setMsg("Topic为空");
            return responseData;
        } else {
            if (topic.getTopicId() < 1) {
                long newTopicId = snowflakeIdWorker.nextId();
                topic.setTopicId(newTopicId);
            }
            ResponseData responseData = doSave(topic);
            return responseData;
        }
    }

    public Topic findById(long topicId) {
        return topicRepository.findById(topicId);
    }

    public void test() {
        testTopic();
        testAnotherTopic();
    }

    private void testTopic() {
        Topic topic = new Topic();
//        long topicId = snowflakeIdWorker.nextId();
        long topicId = 456058615476781056L;
        topic.setTopicId(topicId);
        topic.setTitle("奇葩的处女座1");

        String introduction = "1.挑剔又追求完美.  \n" +
                "2.有丰富的知性，做事一丝不苟.  \n" +
                "3.有旺盛的批判精神,是个完美主义者.  \n" +
                "4.极度的厌恶虚伪与不正当的事.  \n" +
                "5.做事周到、细心、谨慎而有条理，并非常理性，甚至冷酷  \n" +
                "6.做什么事都很投入，而且好学、好奇、求知欲旺盛  \n" +
                "7.外表安静沉默  \n" +
                "8.对任何事都有一套详细的规划\n";
        String content = "处女座可能看起来很凶，其实内心是最柔软的;；处女座看起来很冷淡，但那只是保护自己的方法；" +
                "处女座很重视友情，但被伤害后绝对不再友善；处女座很容易被感动，但感动中又保有理智；处女座可能看起来" +
                "很坚强，其实是最脆弱的；处女座不爱记仇，但谁对她好谁对她不好，处女座会记得很清楚的。" +
                "\n\n处女座很特别,面对突然其来的事件可以比任何人都冷静,处理得井井有条,令人咋舌.面对感情却犹豫不决,怕自己受伤又怕伤害别人,喜欢别人实际性的关心,不喜欢物质的引诱,不清楚自己想要什麽但十分清楚自己不想要什麽.不给自己不喜欢的人丝毫机会,对自己喜欢的人超级纵容。" +
                "\n\n处女座看似冲动直率，其实是性格天真可爱；处女座看似没心没肺，其实挺会心疼人的；处女座看似大条神经，其实是搞怪天才；处女座看似热情奔放，其实对感情保守专一；处女座看似坚强洒脱，其实很害怕失去；处女座看似无理取闹，其实只想你懂他的心。" +
                "\n\n处女座其实很痛苦。表面上总是很有活力，很快乐的样子，可是没人的时候他们又总是很忧伤。处女座总会被一种莫名的悲伤笼罩，但他们不会让别人发现。处女座的人很怕被伤害怕，被抛弃，也怕带给别人伤害和不快乐，只能自己硬挺着一切。因为他承受了太多的东西。" +
                "\n\n处女座有时很忧郁，也许在外表上嘻嘻哈哈，但当自己一个人的时候，可以莫名哭泣，也许会故意找伤心的事回忆，感叹命运的悲惨，然后第二天再抱着饱满的精神面对朋友们，那种气氛也许是可以营造出的悲惨，给自己的脆弱找个理由，告诉自己如果没有那些事会很坚强，的确很坚强，至少不愿让别人看到眼泪。";

        String img = "/app/app-data/star_6_chunv.png";
        topic.setIntroduction(introduction);

        Date nowDate = new Date();
        topic.setCreateTime(nowDate);
        topic.setUpdateTime(nowDate);
        topic.setImg(img);
        topic.setContent(content);

        doSave(topic);
    }

    private void testAnotherTopic() {
        Topic topic = new Topic();
//        long topicId = snowflakeIdWorker.nextId();
        long topicId = 456058616189812736L;
        topic.setTopicId(topicId);
        topic.setTitle("狮子座男生的性格分析");

        String introduction = "狮子座的男生，看起来很坚强，不容易让人看见他们的心伤。在感情中就算分手，也会带着无所谓的面具，默默承受一切。";

        String content = "狮子座的男生，看起来很坚强，不容易让人看见他们的心伤。在感情中就算分手，也会带着无所谓的面具，默默承受一切。也正是因为这样，要强的狮子座男生，会让大家一直误以为他们对情伤无所谓，总是可以很快好起来。事实上，狮子座的男生对爱情非常有责任感，一旦爱定某个人，会非常认真付出，把对方当成自己目前的家人，甚至未来的家人，狮子座的男生一旦决定了方向，就不容易改变。如此深情付出，不难想象失恋后的狮子男生承受的这种情伤到底有多伤。在分手之后的日子，不管是一个月，一年，还是十年，甚至一生，每每想起自己曾经真诚深刻的付出，要强的狮子还是会在半夜里暗自神伤，落泪!他们也会有孩子气的一面，最喜欢与自己喜欢的人撒娇，让人无法接受，有些小男人主义。虽然这些让有些人感到好笑，但是这些会增加彼此间的感情，使大家都能够更快地了解彼此。但在外人看来，他们还有很好的领导才能和组织能力，无论处在什么样的环境，他们都是众人的焦点，大家都会自然的去听他们的指挥，因为他们有很强的能力和亲和力，使周围的朋友能够相信他们。在外面他们也需要你的配合，你必须尊敬他，让他感觉到自己处于领导地位，让外面的人认为他很有面子，其实狮子座的男生很好相处的，而且也是很理想的伴侣！";

        String img = "/app/app-data/topic_shizizuo_1.png";
        topic.setIntroduction(introduction);

        Date nowDate = new Date();
        topic.setCreateTime(nowDate);
        topic.setUpdateTime(nowDate);
        topic.setImg(img);
        topic.setContent(content);

        doSave(topic);
    }

    public List<TopicMobileVO> byPageVisible(int page, int limit) {
        int pageValue = page > 0 ? page : 0;
        limit = limit > 0 ? limit : 10;
        PageParam pageParam = new PageParam();
        pageParam.setPage(pageValue);
        pageParam.setLimit(limit);
        List<Topic> topicList = topicRepository.byPageVisible(pageParam);
        List<TopicMobileVO> voList = new ArrayList<TopicMobileVO>();
        if (topicList!=null && topicList.size() >0){
            for (Topic topic: topicList) {
                TopicMobileVO vo = ObjectConverter.topic2MVO(topic);
                // 移动端获取列表的时候，不传送话题的正文，减小传输的字节数，提高传输速度
                vo.setContent(null);
                voList.add(vo);
            }
        }
        return voList;
    }

    public TableData<TopicVO> byPageForTable(int page, int limit) {
        int pageValue = page > 0 ? page -1 : 0;
        limit = limit > 0 ? limit : 10;
        PageParam pageParam = new PageParam();
        pageParam.setPage(pageValue);
        pageParam.setLimit(limit);

        TableData<Topic> tableData = topicRepository.byPage(pageParam);
        List<Topic> topicList = tableData.getData();

        List<TopicVO> voList = new ArrayList<TopicVO>();
        if (topicList != null && topicList.size() > 0) {
            for (Topic topic : topicList) {
                voList.add(ObjectConverter.topic2VO(topic));
            }
        }

        TableData<TopicVO> result = new TableData<>();
        result.setCode(tableData.getCode());
        result.setMsg(tableData.getMsg());
        result.setCount(tableData.getCount());
        result.setData(voList);
        return result;
    }

    public int delete(long id) {
        return topicRepository.delete(id);
    }
}
