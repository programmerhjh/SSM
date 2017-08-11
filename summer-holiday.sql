/*
Navicat MySQL Data Transfer

Source Server         : first
Source Server Version : 50637
Source Host           : 39.108.68.200:3306
Source Database       : summer-holiday

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2017-08-11 16:20:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(10) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(20) NOT NULL,
  `admin_email` varchar(50) NOT NULL,
  `admin_password` varchar(100) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '123456@qq.com', '4QrcOUm6Wau+VuBX8g+IPg==');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(20) NOT NULL AUTO_INCREMENT,
  `comment_user` int(20) NOT NULL,
  `comment_createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comment_comment` text NOT NULL,
  `comment_post` int(20) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `user_comment` (`comment_user`),
  KEY `post_comment` (`comment_post`),
  CONSTRAINT `post_comment` FOREIGN KEY (`comment_post`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_comment` FOREIGN KEY (`comment_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '3', '2017-07-20 00:00:00', '测试1', '2');
INSERT INTO `comment` VALUES ('2', '7', '2017-07-04 00:00:00', '测试2', '5');
INSERT INTO `comment` VALUES ('4', '28', '2017-07-04 19:43:49', '这是一条评论', '1');
INSERT INTO `comment` VALUES ('7', '3', '2017-07-25 21:06:24', '萨达萨达萨达萨达撒', '1');
INSERT INTO `comment` VALUES ('8', '3', '2017-07-25 21:07:47', '萨达是倒萨', '1');
INSERT INTO `comment` VALUES ('9', '3', '2017-07-25 21:11:38', '萨达萨达', '1');
INSERT INTO `comment` VALUES ('10', '3', '2017-07-25 21:37:28', '的萨达萨达', '1');

-- ----------------------------
-- Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `post_id` int(10) NOT NULL AUTO_INCREMENT,
  `post_name` varchar(255) NOT NULL,
  `post_createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `post_author` int(20) NOT NULL,
  `post_clicktimes` int(20) DEFAULT '0',
  `post_post` text NOT NULL,
  `post_category` varchar(20) DEFAULT '暂未分类',
  PRIMARY KEY (`post_id`),
  KEY `user` (`post_author`),
  CONSTRAINT `user` FOREIGN KEY (`post_author`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '基因检测：我们真的想知道结果吗？', '2017-07-19 00:00:00', '3', '41', '这是我永生难忘的通话之一。我妈妈的大哥、我们家的实际领袖约翰(John)舅舅打电话告诉我他得了前列腺癌。那是2006年的冬天，当时我们还没充分意识到他病情的严重性，但很快他的身体明显变得非常糟糕。他一年内就去世了，享年64岁。\r\n\r\n在约翰舅舅的诊断出来后不久，他的两个弟弟也接受了前列腺癌检查，发现他们也患有这种病。艾伦(Alan)舅舅又活了10年，但去年年初，他在与食道癌抗争了很短一段时间后去世了，那时他刚过完59岁生日没几周。比尔(Bill)舅舅排行老二，他也康复了，但后来他的一个肾上长了一处恶性肿瘤，上个月做了单侧肾切除手术。\r\n\r\n我们家族的人都坚信自己会受到这种遗传性诅咒的折磨。在我们了解到前列腺癌和卵巢癌之间存在明显的基因联系后，这种信念更加根深蒂固——1984年，我母亲死于卵巢癌，当时我出生才9个月，而她只有35岁。\r\n\r\n当约翰舅舅去世时，我从未想过我自己也可能死于癌症的命运；和大多数20多岁的人一样，死亡距离我非常遥远。但随着时间流逝，我开始思考自己是否可能遗传到了这种病的致病基因。直到3年前，当我搬到纽约，为英国《金融时报》进行有关医保的报道时，我才发现了找到这类答案的可能——接受遗传性癌症基因检测。遗传检测是个利润丰厚的行业。去年，该行业成立最久、声望最高的巨数遗传公司(Myriad Genetics)在遗传检测方面创收6.32亿美元，其中包括分析28个基因（相关基因缺陷可能导致患癌风险上升）的“我的风险”(myRisk)检测。以我为例，与我关系最大的基因是BRCA1和BRCA2。其中任何一个基因出现有害变异的男性，罹患恶性前列腺癌的风险都会大大上升。\r\n\r\n每5个患有卵巢癌的女性中就有一个人在这类基因上出现了变异。2013年，女演员安吉丽娜•朱丽(Angelina Jolie)在进行巨数遗传检测后接受了预防性双乳切除术。她的检测结果表明，她在BRCA1基因上存在变异，这大大增加了她罹患乳腺癌和卵巢癌的风险。随后她接受了卵巢和输卵管切除手术。\r\n制药业\r\n研发“沉默杀手”克星\r\n全球制药商押注，未来数年超重人群中比较多见的非酒精性脂肪性肝炎患者大增，带来每年350亿美元的新药市场。\r\n\r\n想要进行遗传检测的人，第一步是寻求遗传顾问的帮助。遗传顾问是指通过梳理个人的家族遗传史来发现患病风险的科学家。我的顾问是巨数遗传的谢利•卡明斯(Shelly Cummings)，不过其他顾问在医院或学术机构工作。卡明斯多次打电话问我关于每个诊断出癌症的家庭成员的情况：第一个肿瘤出现在体内什么位置？扩散了吗？是否已死于那个肿瘤？从确诊到去世经过了多久？\r\n\r\n最终她绘制了详细的家族图谱。男性以方块代替，女性以圆圈代替。被诊断出癌症的人打上了阴影，而死于癌症的人则用一条斜线划掉。我母亲这边的海斯(Hays)家族情况堪忧。在7个核心家庭成员中——父母和5个孩子——5人被诊断出患有某种癌症，其中只有一人逃过一劫。\r\n\r\n海斯家族：本文作者大卫•克罗的母系亲属\r\n\r\n我的姨妈阿尔玛(Alma)是海斯家5个孩子中唯一一个从未诊断出癌症的人。她的母亲、我的外婆也死于癌症。她不用看图谱也能体会到那种失去的痛苦。“看着所有照片，发现自己差不多是唯一一个还活着的人，”她说。“这很可怕，你会问自己为什么老天爷没有像摧毁我们家一样摧毁其他家庭。”', '其他');
INSERT INTO `post` VALUES ('2', '测试2', '2014-06-16 00:00:00', '8', '3', 'asdasfafasfasfsa', '军事');
INSERT INTO `post` VALUES ('4', '测试3', '2017-07-03 00:00:00', '3', '33', 'sadasfsafasfas', '游戏');
INSERT INTO `post` VALUES ('5', 'fgh', '2017-07-22 00:00:00', '3', '33', 'sasadsa', 'sas');
INSERT INTO `post` VALUES ('6', '测试', '2017-08-06 12:16:37', '3', '32', '这是一个测试<img src=\"http://localhost:8080/img/10e110bf-f75f-4e4f-9b0e-c07068aa2bcb2017-07-23-651214013.jpeg\" style=\"max-width:25%;\"><img src=\"http://39.108.68.200:8088/images/894a0f6c-1be8-4f48-94cc-df9016483c5e2017-07-231058223067.jpeg\" style=\"max-width:25%;\"><p><br></p>', '测试类');
INSERT INTO `post` VALUES ('7', 'ceshi', '2017-02-22 00:00:00', '8', '21', '测试', '爱情');
INSERT INTO `post` VALUES ('8', '啦啦', '2017-07-23 00:00:00', '9', '29', 'dasd', 'dddd');
INSERT INTO `post` VALUES ('9', '飒飒', '2017-06-28 00:00:00', '28', '32', 'sdas', 'das');
INSERT INTO `post` VALUES ('11', '今天很烦', '2017-07-24 00:00:00', '3', '0', '<i class=\"w-e-icon-redo\"><i>        </i></i>', '心情');
INSERT INTO `post` VALUES ('12', '萨达萨达撒', '2017-08-06 12:16:55', '3', '0', '萨达四大四大四<strike>大四大四大四大<br><img src=\"http://localhost:8080/img/ce8110c1-0e15-4621-aca5-823113b30e222017-07-24-651214013.jpeg\" style=\"max-width:25%;\"><img src=\"http://localhost:8080/img/c0a9214d-a628-4d52-a713-776fee8d241b2017-07-241058223067.jpeg\" style=\"max-width:25%;\"><img src=\"http://localhost:8080/img/1a5b4aac-be0a-42e8-b986-98ed4cdcfb122017-07-241061917151.jpeg\" style=\"max-width:25%;\"><br><img src=\"http://39.108.68.200:8088/images/13266404-06c3-4cf0-a29c-5ec172fff7f02017-07-241063764193.jpeg\" style=\"max-width:25%;\"><br>尴尬<br><br></strike><p><br></p>', '无');
INSERT INTO `post` VALUES ('14', 'ssasas', '2017-07-26 23:20:53', '30', '12', 'asdas', '暂未分类');
INSERT INTO `post` VALUES ('15', 'sasasas', '2017-07-05 23:21:05', '9', '21', 'asdas', '暂未分类');
INSERT INTO `post` VALUES ('16', '萨达', '2017-07-31 16:17:47', '30', '12', '萨达萨达萨达萨达撒的', '萨达');
INSERT INTO `post` VALUES ('17', '萨达', '2017-07-31 16:17:47', '28', '54', '打四大四大四大大', '飞');
INSERT INTO `post` VALUES ('18', '萨达', '2017-07-31 16:17:47', '3', '54', '阿斯达萨达萨达萨达萨达撒飞洒发', '是否');
INSERT INTO `post` VALUES ('19', '撒刁', '2017-08-11 11:55:41', '8', '45', '萨达是达斯和大师杜哈似乎丢阿叔iahsu', '啊啊');
INSERT INTO `post` VALUES ('20', '萨达', '2017-07-31 16:17:47', '8', '354', '萨达萨达撒家hi粗vhih', '地方');
INSERT INTO `post` VALUES ('21', '索爱的', '2017-08-11 11:57:36', '3', '545', '广泛地和东方红东方', '萨达');
INSERT INTO `post` VALUES ('22', '阿斯达', '2017-08-11 11:55:33', '28', '214', '哈的佛教从新疆农', '阿斯达');
INSERT INTO `post` VALUES ('23', '东风公司', '2017-08-11 11:55:34', '30', '55', '撒懂啊u树导航卡时间段里', '发呆');
INSERT INTO `post` VALUES ('24', '萨达', '2017-07-31 16:17:47', '31', '45', 'sadoiginvoinniosois', '萨芬');

-- ----------------------------
-- Table structure for `reply`
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `reply_id` int(10) NOT NULL AUTO_INCREMENT,
  `reply_comment` int(10) NOT NULL,
  `reply_user` int(10) NOT NULL,
  `reply_post` int(10) NOT NULL,
  `reply_reply` text NOT NULL,
  `reply_createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`reply_id`),
  KEY `r_user` (`reply_user`),
  KEY `r_post` (`reply_post`),
  KEY `r_comment` (`reply_comment`),
  CONSTRAINT `r_comment` FOREIGN KEY (`reply_comment`) REFERENCES `comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `r_post` FOREIGN KEY (`reply_post`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `r_user` FOREIGN KEY (`reply_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('1', '4', '3', '4', 'adasdas', '2017-07-18 17:12:13');
INSERT INTO `reply` VALUES ('7', '1', '3', '1', 'sadas', '2017-07-25 14:58:20');
INSERT INTO `reply` VALUES ('8', '1', '3', '1', 'asdasd', '2017-07-25 15:03:20');
INSERT INTO `reply` VALUES ('9', '2', '3', '1', '萨达是', '2017-07-25 15:45:15');
INSERT INTO `reply` VALUES ('10', '1', '3', '1', '萨达是', '2017-07-25 15:46:55');
INSERT INTO `reply` VALUES ('11', '1', '3', '1', '啊实打实', '2017-07-25 15:48:18');
INSERT INTO `reply` VALUES ('12', '1', '3', '1', 'sadsad', '2017-07-25 15:54:46');
INSERT INTO `reply` VALUES ('13', '1', '3', '1', 'sadsa', '2017-07-25 15:59:53');
INSERT INTO `reply` VALUES ('14', '1', '3', '1', 'asdasdasd', '2017-07-25 16:08:45');
INSERT INTO `reply` VALUES ('15', '1', '3', '1', 'asdasdasdsadsa', '2017-07-25 16:11:20');
INSERT INTO `reply` VALUES ('16', '2', '3', '1', 'sad', '2017-07-25 16:16:48');
INSERT INTO `reply` VALUES ('17', '2', '3', '1', 'sadsadas', '2017-07-25 16:17:06');
INSERT INTO `reply` VALUES ('18', '1', '3', '1', 'asdasdasdsadsasad', '2017-07-25 16:18:41');
INSERT INTO `reply` VALUES ('19', '1', '3', '1', 'asdasdasdsadsasaddas', '2017-07-25 16:19:31');
INSERT INTO `reply` VALUES ('20', '2', '3', '1', 'sadsadas', '2017-07-25 16:22:47');
INSERT INTO `reply` VALUES ('21', '2', '3', '1', 'sadsadassad', '2017-07-25 16:23:20');
INSERT INTO `reply` VALUES ('22', '1', '3', '1', 'sdasd', '2017-07-25 16:35:30');
INSERT INTO `reply` VALUES ('23', '1', '3', '1', 'sadas', '2017-07-25 16:37:35');
INSERT INTO `reply` VALUES ('24', '1', '3', '1', 'sadas', '2017-07-25 16:43:16');
INSERT INTO `reply` VALUES ('25', '1', '3', '1', 'dsadsa', '2017-07-25 16:44:15');
INSERT INTO `reply` VALUES ('26', '1', '3', '1', 'sadcz', '2017-07-25 16:47:12');
INSERT INTO `reply` VALUES ('27', '1', '3', '1', 'sadsadsa', '2017-07-25 16:48:13');
INSERT INTO `reply` VALUES ('28', '1', '3', '1', 'sadsadsadsa', '2017-07-25 16:49:25');
INSERT INTO `reply` VALUES ('29', '1', '3', '1', 'sadsadsadsasa', '2017-07-25 16:50:36');
INSERT INTO `reply` VALUES ('30', '1', '3', '1', '测试啊啊', '2017-07-25 16:51:57');
INSERT INTO `reply` VALUES ('31', '1', '3', '1', '测试啊啊倒萨', '2017-07-25 16:52:58');
INSERT INTO `reply` VALUES ('32', '1', '3', '1', '测试啊啊倒萨', '2017-07-25 16:53:19');
INSERT INTO `reply` VALUES ('33', '1', '3', '1', '测试啊啊倒萨', '2017-07-25 16:54:53');
INSERT INTO `reply` VALUES ('34', '1', '3', '1', '测试啊啊倒萨', '2017-07-25 16:54:56');
INSERT INTO `reply` VALUES ('35', '1', '3', '1', '测试啊啊倒萨', '2017-07-25 16:55:25');
INSERT INTO `reply` VALUES ('36', '1', '3', '1', '测试啊啊倒萨', '2017-07-25 16:56:08');
INSERT INTO `reply` VALUES ('37', '1', '3', '1', '测试啊啊倒萨', '2017-07-25 16:57:09');
INSERT INTO `reply` VALUES ('38', '1', '3', '1', '测试啊啊倒萨', '2017-07-25 16:57:28');
INSERT INTO `reply` VALUES ('39', '2', '3', '1', '测试测试', '2017-07-25 18:13:19');
INSERT INTO `reply` VALUES ('40', '1', '3', '1', '啊飒飒大', '2017-07-25 18:16:16');
INSERT INTO `reply` VALUES ('41', '1', '3', '1', '啊飒飒大萨达', '2017-07-25 18:20:46');
INSERT INTO `reply` VALUES ('42', '1', '3', '1', '啊飒飒大萨达', '2017-07-25 18:20:52');
INSERT INTO `reply` VALUES ('43', '1', '3', '1', 'asadas', '2017-07-25 18:43:06');
INSERT INTO `reply` VALUES ('44', '4', '3', '1', 'sadsadsa', '2017-07-25 18:50:49');
INSERT INTO `reply` VALUES ('49', '4', '3', '1', '<h3 style=\'color: #33cccc\'>回复了6楼</h3><br><br>哈哈', '2017-07-25 21:11:56');
INSERT INTO `reply` VALUES ('50', '7', '3', '1', '<h3 style=\'color: #33cccc\'>回复了4楼</h3><br><br>萨达是', '2017-07-25 21:15:55');
INSERT INTO `reply` VALUES ('51', '10', '3', '1', '萨达是', '2017-07-25 21:39:00');
INSERT INTO `reply` VALUES ('52', '10', '3', '1', '<h3 style=\'color: #33cccc\'>回复了1楼</h3><br><br>大声道', '2017-07-25 21:39:06');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(12) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT ' ',
  `password` varchar(100) DEFAULT NULL,
  `birth` date DEFAULT '1970-01-01',
  `description` varchar(1000) DEFAULT ' ',
  `age` int(3) DEFAULT '0',
  `address` varchar(50) DEFAULT NULL,
  `headaddress` varchar(1000) DEFAULT 'http://39.108.68.200:8088/images/default.jpg',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', 'Tom', '15819696097', '605594106@qq.com', '4QrcOUm6Wau+VuBX8g+IPg==', '1997-10-11', '', '12', 'sadas', 'http://39.108.68.200:8088/images/86ed16ac-a347-4f2b-9e9c-31c0d18c94292017-08-06-942941584.jpeg', '2017-08-06 12:42:31');
INSERT INTO `user` VALUES ('7', 'JERRY', ' ', ' ', '1564', '2017-07-26', ' ', '0', ' ', 'http://39.108.68.200:8088/images/default.jpg', '2017-08-06 12:18:29');
INSERT INTO `user` VALUES ('8', 'JERRY', ' ', ' ', '1564', '2017-07-03', ' ', '0', ' ', 'http://39.108.68.200:8088/images/default.jpg', '2017-08-06 12:18:37');
INSERT INTO `user` VALUES ('9', 'JERRY', ' ', ' ', '1564', '2017-07-01', ' ', '0', ' ', 'http://39.108.68.200:8088/images/default.jpg', '2017-08-06 12:18:39');
INSERT INTO `user` VALUES ('10', '张摆好', ' ', ' ', 'ICy5YqxZB1uWSwcVLSNLcA==', '2017-03-21', ' ', '0', ' ', 'http://39.108.68.200:8088/images/default.jpg', '2017-08-06 12:18:39');
INSERT INTO `user` VALUES ('11', 'hjh', '1212', ' ', '4QrcOUm6Wau+VuBX8g+IPg==', '2017-02-13', ' ', '0', ' ', 'http://39.108.68.200:8088/images/default.jpg', '2017-08-06 12:18:40');
INSERT INTO `user` VALUES ('28', 'HJHGG', '', '', '91slbsEmE+3rneOfAdWiFQ==', '2017-06-06', '  ', '0', '', 'http://39.108.68.200:8088/images/default.jpg', '2017-08-06 12:18:40');
INSERT INTO `user` VALUES ('29', 'asdasdas', ' ', ' ', '91slbsEmE+3rneOfAdWiFQ==', '2017-09-28', ' ', '0', ' ', 'http://39.108.68.200:8088/images/default.jpg', '2017-08-06 12:19:25');
INSERT INTO `user` VALUES ('30', 'xzcxz', ' ', ' ', '91slbsEmE+3rneOfAdWiFQ==', '2017-07-19', ' ', '0', ' ', 'http://39.108.68.200:8088/images/default.jpg', '2017-08-06 12:19:26');
INSERT INTO `user` VALUES ('31', 'adc', ' ', ' ', '91slbsEmE+3rneOfAdWiFQ==', '2017-07-06', ' ', '0', ' ', 'http://39.108.68.200:8088/images/default.jpg', '2017-08-06 12:18:42');
INSERT INTO `user` VALUES ('33', '张百豪', ' ', ' ', '91slbsEmE+3rneOfAdWiFQ==', '1970-01-01', ' ', '0', null, 'http://39.108.68.200:8088/images/default.jpg', '2017-08-08 16:23:01');
INSERT INTO `user` VALUES ('34', '刘', ' ', ' ', 'ISGMyneATSuhkiwz4BURBQ==', '1970-01-01', ' ', '0', null, 'http://39.108.68.200:8088/images/default.jpg', '2017-08-08 16:23:10');
INSERT INTO `user` VALUES ('35', '张', null, ' ', '4QrcOUm6Wau+VuBX8g+IPg==', '1970-01-01', ' ', '0', null, 'http://39.108.68.200:8088/images/default.jpg', '2017-08-08 16:30:15');
INSERT INTO `user` VALUES ('36', '还是', '18826226134', ' ', 'ICy5YqxZB1uWSwcVLSNLcA==', '1970-01-01', ' ', '0', null, 'http://39.108.68.200:8088/images/default.jpg', '2017-08-11 12:20:31');

-- ----------------------------
-- Table structure for `userbehavior`
-- ----------------------------
DROP TABLE IF EXISTS `userbehavior`;
CREATE TABLE `userbehavior` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `userId` int(4) NOT NULL,
  `userHasCompleteFormation` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userbehavior
-- ----------------------------
INSERT INTO `userbehavior` VALUES ('2', '3', '1');
INSERT INTO `userbehavior` VALUES ('6', '9', '0');
INSERT INTO `userbehavior` VALUES ('7', '11', '0');
INSERT INTO `userbehavior` VALUES ('9', '28', '1');
INSERT INTO `userbehavior` VALUES ('10', '29', '0');
INSERT INTO `userbehavior` VALUES ('11', '30', '0');
INSERT INTO `userbehavior` VALUES ('12', '31', '0');
INSERT INTO `userbehavior` VALUES ('13', '33', '0');
INSERT INTO `userbehavior` VALUES ('14', '34', '0');
INSERT INTO `userbehavior` VALUES ('15', '35', '0');
INSERT INTO `userbehavior` VALUES ('16', '36', '0');
