/**
 * Created by 郑明亮 on 2020/4/6 17:24.
 */

//

package top.wys.utils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * <ol>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 * @time 2020/4/6 17:24
 * @email mpro@vip.qq.com
 */
public class CourseInfoDTO {

    /**
     * roomInfo : {"iImId":0,"iResourceId":0,"iRoomId":9169,"iStatus":0,"iUserCount":0,"lBeginTime":1561558185,"lEndTime":1561558185,"lWatchSequence":0,"page":{"iColor":0,"iCoursewareId":1561558218,"iHeight":1440,"iOriginalPageId":0,"iPageId":1,"iScrollPosition":0,"iTabulaHeight":9,"iTabulaWidth":16,"iType":1,"iWidth":2560,"isOffline":false,"liveId":0,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_ddbfc95ee4a66e8aaa1786179f7dc76c_2560_1440.jpg?sign=2743afe60049763aa52ad0c336495295&t=1586717940","traces":{},"lSequence":1,"operationType":0},"sName":"【英语】管理类联考<必修课1>","sTeacher":"谷含笑"}
     * sequenceMap : {"1":[{"bytes":"{\"iPageId\":1,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_ddbfc95ee4a66e8aaa1786179f7dc76c_2560_1440.jpg?sign=2743afe60049763aa52ad0c336495295&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558227392":[{"bytes":"{\"iPageId\":1,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_ddbfc95ee4a66e8aaa1786179f7dc76c_2560_1440.jpg?sign=2743afe60049763aa52ad0c336495295&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558227392,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558231029":[{"bytes":"{\"iPageId\":1,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_ddbfc95ee4a66e8aaa1786179f7dc76c_2560_1440.jpg?sign=2743afe60049763aa52ad0c336495295&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558231029,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558241230":[{"bytes":"{\"iPageId\":2,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_7605df8f065577f6ce23f1ec929c03cf_2560_1440.jpg?sign=0eff7abc1c0730edad8051f869c9485f&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558241230,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558254146":[{"bytes":"{\"iPageId\":3,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_a154985692764dc3a2657758ed0f09ee_2560_1440.jpg?sign=52910ad8e774501050afade36af6e7bb&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558254146,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558267875":[{"bytes":"{\"version\":1,\"iType\":5,\"iCoursewareId\":1561558218,\"iPageId\":3,\"iId\":1,\"lSequence\":1561558267875,\"points\":[{\"x\":7217,\"y\":3032},{\"x\":8175,\"y\":5039}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":200}","eType":10006}],"1561558283787":[{"bytes":"{\"iPageId\":4,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190508/1598_7ded3c72cb8fec44739d7f3f1e59f514_2560_1440.jpg?sign=7f65500cbb119ebfe9de393071438d07&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558283787,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558287141":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":4,\"iId\":2,\"lSequence\":1561558287141,\"points\":[{\"x\":3937,\"y\":6322},{\"x\":3937,\"y\":6392},{\"x\":3950,\"y\":6462}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":404}","eType":10006}],"1561558287343":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":4,\"iId\":2,\"lSequence\":1561558287343,\"points\":[{\"x\":3950,\"y\":6462},{\"x\":3963,\"y\":6485},{\"x\":3976,\"y\":6532},{\"x\":3989,\"y\":6532},{\"x\":3989,\"y\":6579},{\"x\":4015,\"y\":6579}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":203}","eType":10006}],"1561558287749":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":4,\"iId\":2,\"lSequence\":1561558287749,\"points\":[{\"x\":4015,\"y\":6579},{\"x\":4015,\"y\":6602},{\"x\":4041,\"y\":6579},{\"x\":4055,\"y\":6579},{\"x\":4055,\"y\":6555},{\"x\":4068,\"y\":6555},{\"x\":4068,\"y\":6532}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":404}","eType":10006}],"1561558287952":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":4,\"iId\":2,\"lSequence\":1561558287952,\"points\":[{\"x\":4068,\"y\":6532},{\"x\":4081,\"y\":6509},{\"x\":4094,\"y\":6509}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":202}","eType":10006}],"1561558288155":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":4,\"iId\":2,\"lSequence\":1561558288155,\"points\":[{\"x\":4081,\"y\":6509},{\"x\":4120,\"y\":6509},{\"x\":4133,\"y\":6485},{\"x\":4133,\"y\":6462},{\"x\":4146,\"y\":6439},{\"x\":4160,\"y\":6392},{\"x\":4173,\"y\":6369},{\"x\":4186,\"y\":6345},{\"x\":4212,\"y\":6322},{\"x\":4212,\"y\":6299}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":204}","eType":10006}],"1561558288357":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":4,\"iId\":2,\"lSequence\":1561558288357,\"points\":[{\"x\":4212,\"y\":6299},{\"x\":4225,\"y\":6299}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":204}","eType":10006}],"1561558299012":[{"bytes":"{\"iPageId\":7,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_0f62ba444ad3e4e8b4aa7d5b4823f3dd_2560_1440.jpg?sign=2d65aabea86556298a4bd1158e98d171&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558299012,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558304114":[{"bytes":"{\"iPageId\":8,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_2faea2c379bf9233287adfaf80829b38_2560_1440.jpg?sign=288b5452029ed7203a593973bca6f545&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558304114,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558307639":[{"bytes":"{\"iPageId\":9,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_9aacb699046c3bef7c26afd9c31577f8_2560_1440.jpg?sign=9d5fb15723af3613bc0003d8af9b3fd7&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558307639,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558377481":[{"bytes":"{\"iPageId\":10,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_ed567872a3d48a112b8a7bd98a2f58a2_2560_1440.jpg?sign=bf23eac303e1575703afdd560ca697f7&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558377481,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558380694":[{"bytes":"{\"iPageId\":11,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_fc8b2a269cf8ab4b561cf397a25a8257_2560_1440.jpg?sign=0c503d0617e968d8186502e5a7cc17e9&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558380694,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558390835":[{"bytes":"{\"iPageId\":12,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_09da44adccb6f7384e129dc8c49da8c3_2560_1440.jpg?sign=3326769c625bf383b078cf6ad89aea08&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558390835,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558398931":[{"bytes":"{\"iPageId\":13,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_985993fdde3df731d86320a4e4a96862_2560_1440.jpg?sign=f0d48508bc70bfd0d65f257796541c94&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558398931,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558400351":[{"bytes":"{\"iPageId\":14,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_e1107d07c98d7b3e7d015f95464a0dc6_2560_1440.jpg?sign=96d8ae97dc3d431de832b9c647b44b8b&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558400351,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558403424":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":3,\"lSequence\":1561558403424,\"points\":[{\"x\":4120,\"y\":4852},{\"x\":4225,\"y\":4852}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":198}","eType":10006}],"1561558403627":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":3,\"lSequence\":1561558403627,\"points\":[{\"x\":4120,\"y\":4852},{\"x\":4265,\"y\":4852},{\"x\":4304,\"y\":4806},{\"x\":4422,\"y\":4806}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":202}","eType":10006}],"1561558403829":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":3,\"lSequence\":1561558403829,\"points\":[{\"x\":4304,\"y\":4806},{\"x\":4475,\"y\":4806},{\"x\":4488,\"y\":4829}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":204}","eType":10006}],"1561558403876":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":3,\"lSequence\":1561558403876,\"points\":[{\"x\":4488,\"y\":4829},{\"x\":4514,\"y\":4829}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":51}","eType":10006}],"1561558405046":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":4,\"lSequence\":1561558405046,\"points\":[{\"x\":5091,\"y\":4899},{\"x\":5288,\"y\":4899}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":210}","eType":10006}],"1561558405249":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":4,\"lSequence\":1561558405249,\"points\":[{\"x\":5091,\"y\":4899},{\"x\":5524,\"y\":4899},{\"x\":5564,\"y\":4922},{\"x\":5629,\"y\":4922}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":194}","eType":10006}],"1561558405374":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":4,\"lSequence\":1561558405374,\"points\":[{\"x\":5629,\"y\":4922},{\"x\":5643,\"y\":4946},{\"x\":5669,\"y\":4946}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":136}","eType":10006}],"1561558407683":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":5,\"lSequence\":1561558407683,\"points\":[{\"x\":4488,\"y\":8165},{\"x\":4501,\"y\":8188},{\"x\":4724,\"y\":8188},{\"x\":4908,\"y\":8212},{\"x\":5000,\"y\":8305},{\"x\":5026,\"y\":8305}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":200}","eType":10006}],"1561558408572":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":6,\"lSequence\":1561558408572,\"points\":[{\"x\":5354,\"y\":8352},{\"x\":5564,\"y\":8352}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":201}","eType":10006}],"1561558408775":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":6,\"lSequence\":1561558408775,\"points\":[{\"x\":5354,\"y\":8352},{\"x\":5682,\"y\":8352}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":201}","eType":10006}],"1561558408977":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":6,\"lSequence\":1561558408977,\"points\":[{\"x\":5354,\"y\":8352},{\"x\":5761,\"y\":8352}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":200}","eType":10006}],"1561558410397":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":7,\"lSequence\":1561558410397,\"points\":[{\"x\":6049,\"y\":8282},{\"x\":6207,\"y\":8282},{\"x\":6430,\"y\":8328}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":193}","eType":10006}],"1561558410569":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":7,\"lSequence\":1561558410569,\"points\":[{\"x\":6430,\"y\":8328},{\"x\":6601,\"y\":8375},{\"x\":6614,\"y\":8375}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":182}","eType":10006}],"1561558411770":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":8,\"lSequence\":1561558411770,\"points\":[{\"x\":5551,\"y\":7022},{\"x\":5524,\"y\":7022},{\"x\":5498,\"y\":7045},{\"x\":5446,\"y\":7115},{\"x\":5419,\"y\":7185},{\"x\":5393,\"y\":7279},{\"x\":5393,\"y\":7302},{\"x\":5380,\"y\":7372},{\"x\":5380,\"y\":7395},{\"x\":5367,\"y\":7442}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":193}","eType":10006}],"1561558411973":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":8,\"lSequence\":1561558411973,\"points\":[{\"x\":5380,\"y\":7395},{\"x\":5354,\"y\":7489},{\"x\":5472,\"y\":7489}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":209}","eType":10006}],"1561558412175":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":8,\"lSequence\":1561558412175,\"points\":[{\"x\":5354,\"y\":7489},{\"x\":5485,\"y\":7489},{\"x\":5511,\"y\":7465},{\"x\":5524,\"y\":7465},{\"x\":5551,\"y\":7442},{\"x\":5616,\"y\":7442}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":199}","eType":10006}],"1561558412378":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":8,\"lSequence\":1561558412378,\"points\":[{\"x\":5616,\"y\":7442},{\"x\":5616,\"y\":7139}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":200}","eType":10006}],"1561558412581":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":14,\"iId\":8,\"lSequence\":1561558412581,\"points\":[{\"x\":5616,\"y\":7442},{\"x\":5616,\"y\":6929}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":201}","eType":10006}],"1561558413595":[{"bytes":"{\"iPageId\":15,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_e85e9dbfe858399755b0d4911e610df0_2560_1440.jpg?sign=2b6619b468838b624ccae052d71063c1&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558413595,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558433938":[{"bytes":"{\"iPageId\":16,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_5cc4f45bfcc3ec32e47b4a3eec3e3b28_2560_1440.jpg?sign=92d454ab1dad0c945525c942c1b3eec4&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558433938,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558439179":[{"bytes":"{\"iPageId\":17,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_1b3c3f7dff44113f0dea998b95796cd9_2560_1440.jpg?sign=bb5efac3b381ebaac78e9ad9d2212584&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558439179,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558444390":[{"bytes":"{\"iPageId\":18,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_ec2c586c5e4821a35da5cafc7267d849_2560_1440.jpg?sign=7bf8f0374ebf1bbe4da9fdd8d1c0c011&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558444390,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558449304":[{"bytes":"{\"iPageId\":19,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_7d9c04cad8f6e5d1ac38d88eaf54a348_2560_1440.jpg?sign=04974addf160ff7f6a9c170c60b778dc&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558449304,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558460801":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":19,\"iId\":9,\"lSequence\":1561558460801,\"points\":[{\"x\":5183,\"y\":5272},{\"x\":5209,\"y\":5272},{\"x\":5367,\"y\":5202},{\"x\":5485,\"y\":5202}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":203}","eType":10006}],"1561558461004":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":19,\"iId\":9,\"lSequence\":1561558461004,\"points\":[{\"x\":5485,\"y\":5202},{\"x\":5551,\"y\":5179},{\"x\":5577,\"y\":5156},{\"x\":5590,\"y\":5156}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":213}","eType":10006}],"1561558461066":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":19,\"iId\":9,\"lSequence\":1561558461066,\"points\":[{\"x\":5577,\"y\":5156},{\"x\":5616,\"y\":5156}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":52}","eType":10006}],"1561558462143":[{"bytes":"{\"iPageId\":20,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_281b7eb74f221fadf498b0b205b752df_2560_1440.jpg?sign=feb4e30fdd590281f4eb0839da6d8fc2&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558462143,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558467072":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":20,\"iId\":10,\"lSequence\":1561558467072,\"points\":[{\"x\":4593,\"y\":5762},{\"x\":4593,\"y\":5972},{\"x\":4606,\"y\":6042},{\"x\":4645,\"y\":6089},{\"x\":4645,\"y\":6182},{\"x\":4658,\"y\":6205},{\"x\":4658,\"y\":6299},{\"x\":4671,\"y\":6322},{\"x\":4671,\"y\":6369},{\"x\":4685,\"y\":6439},{\"x\":4698,\"y\":6485}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":189}","eType":10006}],"1561558467275":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":20,\"iId\":10,\"lSequence\":1561558467275,\"points\":[{\"x\":4698,\"y\":6485},{\"x\":4698,\"y\":6509},{\"x\":4711,\"y\":6555},{\"x\":4711,\"y\":6672},{\"x\":4737,\"y\":6695},{\"x\":4737,\"y\":6835},{\"x\":4750,\"y\":6859},{\"x\":4750,\"y\":6929},{\"x\":4763,\"y\":6975},{\"x\":4763,\"y\":7069},{\"x\":4776,\"y\":7092},{\"x\":4776,\"y\":7209}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":209}","eType":10006}],"1561558467478":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":20,\"iId\":10,\"lSequence\":1561558467478,\"points\":[{\"x\":4776,\"y\":7092},{\"x\":4776,\"y\":7535},{\"x\":4803,\"y\":7699},{\"x\":4803,\"y\":8445}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":200}","eType":10006}],"1561558467681":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":20,\"iId\":10,\"lSequence\":1561558467681,\"points\":[{\"x\":4803,\"y\":7699},{\"x\":4803,\"y\":8678},{\"x\":4829,\"y\":8772},{\"x\":4842,\"y\":8888},{\"x\":4842,\"y\":9005},{\"x\":4855,\"y\":9052},{\"x\":4855,\"y\":9075}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":201}","eType":10006}],"1561558476261":[{"bytes":"{\"iPageId\":21,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_5a5eb8ec39034a749be89ae9a2dd9208_2560_1440.jpg?sign=04618922a70c41548203a6a69a6f1144&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558476261,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558483780":[{"bytes":"{\"iPageId\":22,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_6f8e9b611564b2fabbdf987cbb967785_2560_1440.jpg?sign=6add789431a4201906eebc7177e97b49&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558483780,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558485933":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":22,\"iId\":11,\"lSequence\":1561558485933,\"points\":[{\"x\":6181,\"y\":5739},{\"x\":6181,\"y\":5785},{\"x\":6167,\"y\":5809},{\"x\":6154,\"y\":5879},{\"x\":6141,\"y\":5902},{\"x\":6102,\"y\":5972},{\"x\":6089,\"y\":6019},{\"x\":6076,\"y\":6019},{\"x\":6076,\"y\":6112},{\"x\":6062,\"y\":6182},{\"x\":6062,\"y\":6252}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":202}","eType":10006}],"1561558486136":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":22,\"iId\":11,\"lSequence\":1561558486136,\"points\":[{\"x\":6062,\"y\":6252},{\"x\":6036,\"y\":6299},{\"x\":6036,\"y\":6415},{\"x\":6023,\"y\":6439},{\"x\":6010,\"y\":6485},{\"x\":6010,\"y\":6579},{\"x\":5997,\"y\":6602},{\"x\":5997,\"y\":6649},{\"x\":5984,\"y\":6672},{\"x\":5984,\"y\":6719}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":203}","eType":10006}],"1561558487524":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":22,\"iId\":12,\"lSequence\":1561558487524,\"points\":[{\"x\":5656,\"y\":6789},{\"x\":5643,\"y\":6812},{\"x\":5616,\"y\":6905},{\"x\":5590,\"y\":7045},{\"x\":5577,\"y\":7232},{\"x\":5538,\"y\":7372},{\"x\":5538,\"y\":7629}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":199}","eType":10006}],"1561558487727":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":22,\"iId\":12,\"lSequence\":1561558487727,\"points\":[{\"x\":5538,\"y\":7629},{\"x\":5524,\"y\":7675},{\"x\":5524,\"y\":7722}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":195}","eType":10006}],"1561558492032":[{"bytes":"{\"iPageId\":23,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_68a645b7ba9d2206877b3393500554ee_2560_1440.jpg?sign=ecfa2833eab6a1fd5f5837841ff73e0e&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558492032,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558514824":[{"bytes":"{\"iPageId\":24,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_c6a4d431d261039fdf1cde4e657d6329_2560_1440.jpg?sign=e72c28f84e1d821e8f0431d97463f135&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558514824,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558517476":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":24,\"iId\":13,\"lSequence\":1561558517476,\"points\":[{\"x\":5695,\"y\":5902},{\"x\":5656,\"y\":5902},{\"x\":5643,\"y\":5925},{\"x\":5616,\"y\":5925},{\"x\":5603,\"y\":5949},{\"x\":5551,\"y\":6042},{\"x\":5551,\"y\":6112},{\"x\":5524,\"y\":6182},{\"x\":5511,\"y\":6229},{\"x\":5498,\"y\":6275},{\"x\":5498,\"y\":6369},{\"x\":5485,\"y\":6415},{\"x\":5485,\"y\":6439},{\"x\":5472,\"y\":6462},{\"x\":5472,\"y\":6485}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":194}","eType":10006}],"1561558517679":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":24,\"iId\":13,\"lSequence\":1561558517679,\"points\":[{\"x\":5472,\"y\":6462},{\"x\":5472,\"y\":6672}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":202}","eType":10006}],"1561558519426":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":24,\"iId\":14,\"lSequence\":1561558519426,\"points\":[{\"x\":5616,\"y\":6975},{\"x\":5616,\"y\":7045},{\"x\":5564,\"y\":7185},{\"x\":5564,\"y\":7232},{\"x\":5551,\"y\":7325},{\"x\":5551,\"y\":7442},{\"x\":5511,\"y\":7675},{\"x\":5498,\"y\":7722},{\"x\":5498,\"y\":7839},{\"x\":5485,\"y\":7862}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":401}","eType":10006}],"1561558520627":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":24,\"iId\":15,\"lSequence\":1561558520627,\"points\":[{\"x\":5590,\"y\":7722},{\"x\":5603,\"y\":7722},{\"x\":5603,\"y\":7769},{\"x\":5590,\"y\":7909},{\"x\":5577,\"y\":8025},{\"x\":5551,\"y\":8118},{\"x\":5551,\"y\":8188},{\"x\":5538,\"y\":8235},{\"x\":5511,\"y\":8258},{\"x\":5511,\"y\":8282}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":206}","eType":10006}],"1561558520799":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":24,\"iId\":15,\"lSequence\":1561558520799,\"points\":[{\"x\":5511,\"y\":8258},{\"x\":5511,\"y\":8328}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":167}","eType":10006}],"1561558521532":[{"bytes":"{\"iPageId\":25,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_31324aca39906037b4888f1afedd2c4a_2560_1440.jpg?sign=42fb4ab8510261bc80fca9366a5f1a2a&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558521532,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558526399":[{"bytes":"{\"iPageId\":26,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_076b5f737f144c4395fe00ef4634d361_2560_1440.jpg?sign=738e9a64971285ecfc7e6850849bcd01&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558526399,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558530877":[{"bytes":"{\"iPageId\":31,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_5f2660f5c001760ee4c15de3dee859c0_2560_1440.jpg?sign=459047f2597360157eb8b4fed3ada94f&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558530877,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558535182":[{"bytes":"{\"iPageId\":38,\"sUrl\":\"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_6765ffdbc7aff622916d1f1feb20b118_2560_1440.jpg?sign=dd7a60de2e9bf3d5524434181a255014&t=1586717940\",\"iType\":1,\"traces\":{},\"iWidth\":2560,\"iHeight\":1440,\"lSequence\":1561558535182,\"iCoursewareId\":1561558218,\"iTabulaWidth\":16,\"iTabulaHeight\":9,\"iScrollPosition\":50}","eType":10008}],"1561558541547":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":38,\"iId\":16,\"lSequence\":1561558541547,\"points\":[{\"x\":7034,\"y\":4969},{\"x\":7099,\"y\":4969},{\"x\":7139,\"y\":4992},{\"x\":7362,\"y\":4992}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":203}","eType":10006}],"1561558541750":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":38,\"iId\":16,\"lSequence\":1561558541750,\"points\":[{\"x\":7139,\"y\":4992},{\"x\":7664,\"y\":4992},{\"x\":7821,\"y\":5062},{\"x\":7887,\"y\":5086},{\"x\":8005,\"y\":5109},{\"x\":8097,\"y\":5132},{\"x\":8123,\"y\":5132}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":200}","eType":10006}],"1561558541890":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":38,\"iId\":16,\"lSequence\":1561558541890,\"points\":[{\"x\":8097,\"y\":5132},{\"x\":8136,\"y\":5132}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":148}","eType":10006}],"1561558542904":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":38,\"iId\":17,\"lSequence\":1561558542904,\"points\":[{\"x\":7060,\"y\":9028},{\"x\":7598,\"y\":9028},{\"x\":7769,\"y\":9052},{\"x\":7900,\"y\":9075},{\"x\":8005,\"y\":9075},{\"x\":8031,\"y\":9098}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":203}","eType":10006}],"1561558543107":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":38,\"iId\":17,\"lSequence\":1561558543107,\"points\":[{\"x\":8031,\"y\":9098},{\"x\":8871,\"y\":9098}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":201}","eType":10006}],"1561558543310":[{"bytes":"{\"version\":1,\"iType\":1,\"iCoursewareId\":1561558218,\"iPageId\":38,\"iId\":17,\"lSequence\":1561558543310,\"points\":[{\"x\":8031,\"y\":9098},{\"x\":9146,\"y\":9098}],\"penWidth\":6,\"iColor\":4294901760,\"sText\":\"\",\"iDuration\":204}","eType":10006}]}
     * videoPlayUrls : [{"bIsConcat":false,"eCdnType":1,"eMediaType":1,"iBitrate":0,"iDuration":344.1,"lFileSize":12495803,"lSequence":1561558230450,"sFileName":"5285890793612291017","sFormat":"mp4","sHttpsUrl":"https://1257236654.vod2.myqcloud.com/cc26eeabvodcq1257236654/130b772a5285890793612291017/f0.mp4?t=5e8a2a74&us=d923fa75&sign=d5e15b0eb7a7f9e61005f90cf5e9158a","sUrl":"http://1257236654.vod2.myqcloud.com/cc26eeabvodcq1257236654/130b772a5285890793612291017/f0.mp4?t=5e8a2a74&us=d923fa75&sign=d5e15b0eb7a7f9e61005f90cf5e9158a","sequenceInfos":[{"iDuration":344.1,"iStart":0,"lSequence":1561558230450}],"sharedPlaybackUrlInfos":[]}]
     */

    private RoomInfoBean roomInfo;
    private SequenceMapBean sequenceMap;
    private List<VideoPlayUrlsBean> videoPlayUrls;

    public RoomInfoBean getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(RoomInfoBean roomInfo) {
        this.roomInfo = roomInfo;
    }

    public SequenceMapBean getSequenceMap() {
        return sequenceMap;
    }

    public void setSequenceMap(SequenceMapBean sequenceMap) {
        this.sequenceMap = sequenceMap;
    }

    public List<VideoPlayUrlsBean> getVideoPlayUrls() {
        return videoPlayUrls;
    }

    public void setVideoPlayUrls(List<VideoPlayUrlsBean> videoPlayUrls) {
        this.videoPlayUrls = videoPlayUrls;
    }

    public static class RoomInfoBean {
        /**
         * iImId : 0
         * iResourceId : 0
         * iRoomId : 9169
         * iStatus : 0
         * iUserCount : 0
         * lBeginTime : 1561558185
         * lEndTime : 1561558185
         * lWatchSequence : 0
         * page : {"iColor":0,"iCoursewareId":1561558218,"iHeight":1440,"iOriginalPageId":0,"iPageId":1,"iScrollPosition":0,"iTabulaHeight":9,"iTabulaWidth":16,"iType":1,"iWidth":2560,"isOffline":false,"liveId":0,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_ddbfc95ee4a66e8aaa1786179f7dc76c_2560_1440.jpg?sign=2743afe60049763aa52ad0c336495295&t=1586717940","traces":{},"lSequence":1,"operationType":0}
         * sName : 【英语】管理类联考<必修课1>
         * sTeacher : 谷含笑
         */

        private String iImId;
        private String iResourceId;
        private String iRoomId;
        private String iStatus;
        private String iUserCount;
        private String lBeginTime;
        private String lEndTime;
        private String lWatchSequence;
        private PageBean page;
        private String sName;
        private String sTeacher;

        public String getIImId() {
            return iImId;
        }

        public void setIImId(String iImId) {
            this.iImId = iImId;
        }

        public String getIResourceId() {
            return iResourceId;
        }

        public void setIResourceId(String iResourceId) {
            this.iResourceId = iResourceId;
        }

        public String getIRoomId() {
            return iRoomId;
        }

        public void setIRoomId(String iRoomId) {
            this.iRoomId = iRoomId;
        }

        public String getIStatus() {
            return iStatus;
        }

        public void setIStatus(String iStatus) {
            this.iStatus = iStatus;
        }

        public String getIUserCount() {
            return iUserCount;
        }

        public void setIUserCount(String iUserCount) {
            this.iUserCount = iUserCount;
        }

        public String getLBeginTime() {
            return lBeginTime;
        }

        public void setLBeginTime(String lBeginTime) {
            this.lBeginTime = lBeginTime;
        }

        public String getLEndTime() {
            return lEndTime;
        }

        public void setLEndTime(String lEndTime) {
            this.lEndTime = lEndTime;
        }

        public String getLWatchSequence() {
            return lWatchSequence;
        }

        public void setLWatchSequence(String lWatchSequence) {
            this.lWatchSequence = lWatchSequence;
        }

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public String getSName() {
            return sName;
        }

        public void setSName(String sName) {
            this.sName = sName;
        }

        public String getSTeacher() {
            return sTeacher;
        }

        public void setSTeacher(String sTeacher) {
            this.sTeacher = sTeacher;
        }

        public static class PageBean {
            /**
             * iColor : 0
             * iCoursewareId : 1561558218
             * iHeight : 1440
             * iOriginalPageId : 0
             * iPageId : 1
             * iScrollPosition : 0
             * iTabulaHeight : 9
             * iTabulaWidth : 16
             * iType : 1
             * iWidth : 2560
             * isOffline : false
             * liveId : 0
             * sUrl : //sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_ddbfc95ee4a66e8aaa1786179f7dc76c_2560_1440.jpg?sign=2743afe60049763aa52ad0c336495295&t=1586717940
             * traces : {}
             * lSequence : 1
             * operationType : 0
             */

            private String iColor;
            private String iCoursewareId;
            private String iHeight;
            private String iOriginalPageId;
            private String iPageId;
            private String iScrollPosition;
            private String iTabulaHeight;
            private String iTabulaWidth;
            private String iType;
            private String iWidth;
            private boolean isOffline;
            private String liveId;
            private String sUrl;
            private TracesBean traces;
            private String lSequence;
            private String operationType;

            public String getIColor() {
                return iColor;
            }

            public void setIColor(String iColor) {
                this.iColor = iColor;
            }

            public String getICoursewareId() {
                return iCoursewareId;
            }

            public void setICoursewareId(String iCoursewareId) {
                this.iCoursewareId = iCoursewareId;
            }

            public String getIHeight() {
                return iHeight;
            }

            public void setIHeight(String iHeight) {
                this.iHeight = iHeight;
            }

            public String getIOriginalPageId() {
                return iOriginalPageId;
            }

            public void setIOriginalPageId(String iOriginalPageId) {
                this.iOriginalPageId = iOriginalPageId;
            }

            public String getIPageId() {
                return iPageId;
            }

            public void setIPageId(String iPageId) {
                this.iPageId = iPageId;
            }

            public String getIScrollPosition() {
                return iScrollPosition;
            }

            public void setIScrollPosition(String iScrollPosition) {
                this.iScrollPosition = iScrollPosition;
            }

            public String getITabulaHeight() {
                return iTabulaHeight;
            }

            public void setITabulaHeight(String iTabulaHeight) {
                this.iTabulaHeight = iTabulaHeight;
            }

            public String getITabulaWidth() {
                return iTabulaWidth;
            }

            public void setITabulaWidth(String iTabulaWidth) {
                this.iTabulaWidth = iTabulaWidth;
            }

            public String getIType() {
                return iType;
            }

            public void setIType(String iType) {
                this.iType = iType;
            }

            public String getIWidth() {
                return iWidth;
            }

            public void setIWidth(String iWidth) {
                this.iWidth = iWidth;
            }

            public boolean isIsOffline() {
                return isOffline;
            }

            public void setIsOffline(boolean isOffline) {
                this.isOffline = isOffline;
            }

            public String getLiveId() {
                return liveId;
            }

            public void setLiveId(String liveId) {
                this.liveId = liveId;
            }

            public String getSUrl() {
                return sUrl;
            }

            public void setSUrl(String sUrl) {
                this.sUrl = sUrl;
            }

            public TracesBean getTraces() {
                return traces;
            }

            public void setTraces(TracesBean traces) {
                this.traces = traces;
            }

            public String getLSequence() {
                return lSequence;
            }

            public void setLSequence(String lSequence) {
                this.lSequence = lSequence;
            }

            public String getOperationType() {
                return operationType;
            }

            public void setOperationType(String operationType) {
                this.operationType = operationType;
            }

            public static class TracesBean {
            }
        }
    }

    public static class SequenceMapBean {
        @SerializedName("1")
        private List<_$1Bean> _$1;
        @SerializedName("1561558227392")
        private List<_$1561558227392Bean> _$1561558227392;
        @SerializedName("1561558231029")
        private List<_$1561558231029Bean> _$1561558231029;
        @SerializedName("1561558241230")
        private List<_$1561558241230Bean> _$1561558241230;
        @SerializedName("1561558254146")
        private List<_$1561558254146Bean> _$1561558254146;
        @SerializedName("1561558267875")
        private List<_$1561558267875Bean> _$1561558267875;
        @SerializedName("1561558283787")
        private List<_$1561558283787Bean> _$1561558283787;
        @SerializedName("1561558287141")
        private List<_$1561558287141Bean> _$1561558287141;
        @SerializedName("1561558287343")
        private List<_$1561558287343Bean> _$1561558287343;
        @SerializedName("1561558287749")
        private List<_$1561558287749Bean> _$1561558287749;
        @SerializedName("1561558287952")
        private List<_$1561558287952Bean> _$1561558287952;
        @SerializedName("1561558288155")
        private List<_$1561558288155Bean> _$1561558288155;
        @SerializedName("1561558288357")
        private List<_$1561558288357Bean> _$1561558288357;
        @SerializedName("1561558299012")
        private List<_$1561558299012Bean> _$1561558299012;
        @SerializedName("1561558304114")
        private List<_$1561558304114Bean> _$1561558304114;
        @SerializedName("1561558307639")
        private List<_$1561558307639Bean> _$1561558307639;
        @SerializedName("1561558377481")
        private List<_$1561558377481Bean> _$1561558377481;
        @SerializedName("1561558380694")
        private List<_$1561558380694Bean> _$1561558380694;
        @SerializedName("1561558390835")
        private List<_$1561558390835Bean> _$1561558390835;
        @SerializedName("1561558398931")
        private List<_$1561558398931Bean> _$1561558398931;
        @SerializedName("1561558400351")
        private List<_$1561558400351Bean> _$1561558400351;
        @SerializedName("1561558403424")
        private List<_$1561558403424Bean> _$1561558403424;
        @SerializedName("1561558403627")
        private List<_$1561558403627Bean> _$1561558403627;
        @SerializedName("1561558403829")
        private List<_$1561558403829Bean> _$1561558403829;
        @SerializedName("1561558403876")
        private List<_$1561558403876Bean> _$1561558403876;
        @SerializedName("1561558405046")
        private List<_$1561558405046Bean> _$1561558405046;
        @SerializedName("1561558405249")
        private List<_$1561558405249Bean> _$1561558405249;
        @SerializedName("1561558405374")
        private List<_$1561558405374Bean> _$1561558405374;
        @SerializedName("1561558407683")
        private List<_$1561558407683Bean> _$1561558407683;
        @SerializedName("1561558408572")
        private List<_$1561558408572Bean> _$1561558408572;
        @SerializedName("1561558408775")
        private List<_$1561558408775Bean> _$1561558408775;
        @SerializedName("1561558408977")
        private List<_$1561558408977Bean> _$1561558408977;
        @SerializedName("1561558410397")
        private List<_$1561558410397Bean> _$1561558410397;
        @SerializedName("1561558410569")
        private List<_$1561558410569Bean> _$1561558410569;
        @SerializedName("1561558411770")
        private List<_$1561558411770Bean> _$1561558411770;
        @SerializedName("1561558411973")
        private List<_$1561558411973Bean> _$1561558411973;
        @SerializedName("1561558412175")
        private List<_$1561558412175Bean> _$1561558412175;
        @SerializedName("1561558412378")
        private List<_$1561558412378Bean> _$1561558412378;
        @SerializedName("1561558412581")
        private List<_$1561558412581Bean> _$1561558412581;
        @SerializedName("1561558413595")
        private List<_$1561558413595Bean> _$1561558413595;
        @SerializedName("1561558433938")
        private List<_$1561558433938Bean> _$1561558433938;
        @SerializedName("1561558439179")
        private List<_$1561558439179Bean> _$1561558439179;
        @SerializedName("1561558444390")
        private List<_$1561558444390Bean> _$1561558444390;
        @SerializedName("1561558449304")
        private List<_$1561558449304Bean> _$1561558449304;
        @SerializedName("1561558460801")
        private List<_$1561558460801Bean> _$1561558460801;
        @SerializedName("1561558461004")
        private List<_$1561558461004Bean> _$1561558461004;
        @SerializedName("1561558461066")
        private List<_$1561558461066Bean> _$1561558461066;
        @SerializedName("1561558462143")
        private List<_$1561558462143Bean> _$1561558462143;
        @SerializedName("1561558467072")
        private List<_$1561558467072Bean> _$1561558467072;
        @SerializedName("1561558467275")
        private List<_$1561558467275Bean> _$1561558467275;
        @SerializedName("1561558467478")
        private List<_$1561558467478Bean> _$1561558467478;
        @SerializedName("1561558467681")
        private List<_$1561558467681Bean> _$1561558467681;
        @SerializedName("1561558476261")
        private List<_$1561558476261Bean> _$1561558476261;
        @SerializedName("1561558483780")
        private List<_$1561558483780Bean> _$1561558483780;
        @SerializedName("1561558485933")
        private List<_$1561558485933Bean> _$1561558485933;
        @SerializedName("1561558486136")
        private List<_$1561558486136Bean> _$1561558486136;
        @SerializedName("1561558487524")
        private List<_$1561558487524Bean> _$1561558487524;
        @SerializedName("1561558487727")
        private List<_$1561558487727Bean> _$1561558487727;
        @SerializedName("1561558492032")
        private List<_$1561558492032Bean> _$1561558492032;
        @SerializedName("1561558514824")
        private List<_$1561558514824Bean> _$1561558514824;
        @SerializedName("1561558517476")
        private List<_$1561558517476Bean> _$1561558517476;
        @SerializedName("1561558517679")
        private List<_$1561558517679Bean> _$1561558517679;
        @SerializedName("1561558519426")
        private List<_$1561558519426Bean> _$1561558519426;
        @SerializedName("1561558520627")
        private List<_$1561558520627Bean> _$1561558520627;
        @SerializedName("1561558520799")
        private List<_$1561558520799Bean> _$1561558520799;
        @SerializedName("1561558521532")
        private List<_$1561558521532Bean> _$1561558521532;
        @SerializedName("1561558526399")
        private List<_$1561558526399Bean> _$1561558526399;
        @SerializedName("1561558530877")
        private List<_$1561558530877Bean> _$1561558530877;
        @SerializedName("1561558535182")
        private List<_$1561558535182Bean> _$1561558535182;
        @SerializedName("1561558541547")
        private List<_$1561558541547Bean> _$1561558541547;
        @SerializedName("1561558541750")
        private List<_$1561558541750Bean> _$1561558541750;
        @SerializedName("1561558541890")
        private List<_$1561558541890Bean> _$1561558541890;
        @SerializedName("1561558542904")
        private List<_$1561558542904Bean> _$1561558542904;
        @SerializedName("1561558543107")
        private List<_$1561558543107Bean> _$1561558543107;
        @SerializedName("1561558543310")
        private List<_$1561558543310Bean> _$1561558543310;

        public List<_$1Bean> get_$1() {
            return _$1;
        }

        public void set_$1(List<_$1Bean> _$1) {
            this._$1 = _$1;
        }

        public List<_$1561558227392Bean> get_$1561558227392() {
            return _$1561558227392;
        }

        public void set_$1561558227392(List<_$1561558227392Bean> _$1561558227392) {
            this._$1561558227392 = _$1561558227392;
        }

        public List<_$1561558231029Bean> get_$1561558231029() {
            return _$1561558231029;
        }

        public void set_$1561558231029(List<_$1561558231029Bean> _$1561558231029) {
            this._$1561558231029 = _$1561558231029;
        }

        public List<_$1561558241230Bean> get_$1561558241230() {
            return _$1561558241230;
        }

        public void set_$1561558241230(List<_$1561558241230Bean> _$1561558241230) {
            this._$1561558241230 = _$1561558241230;
        }

        public List<_$1561558254146Bean> get_$1561558254146() {
            return _$1561558254146;
        }

        public void set_$1561558254146(List<_$1561558254146Bean> _$1561558254146) {
            this._$1561558254146 = _$1561558254146;
        }

        public List<_$1561558267875Bean> get_$1561558267875() {
            return _$1561558267875;
        }

        public void set_$1561558267875(List<_$1561558267875Bean> _$1561558267875) {
            this._$1561558267875 = _$1561558267875;
        }

        public List<_$1561558283787Bean> get_$1561558283787() {
            return _$1561558283787;
        }

        public void set_$1561558283787(List<_$1561558283787Bean> _$1561558283787) {
            this._$1561558283787 = _$1561558283787;
        }

        public List<_$1561558287141Bean> get_$1561558287141() {
            return _$1561558287141;
        }

        public void set_$1561558287141(List<_$1561558287141Bean> _$1561558287141) {
            this._$1561558287141 = _$1561558287141;
        }

        public List<_$1561558287343Bean> get_$1561558287343() {
            return _$1561558287343;
        }

        public void set_$1561558287343(List<_$1561558287343Bean> _$1561558287343) {
            this._$1561558287343 = _$1561558287343;
        }

        public List<_$1561558287749Bean> get_$1561558287749() {
            return _$1561558287749;
        }

        public void set_$1561558287749(List<_$1561558287749Bean> _$1561558287749) {
            this._$1561558287749 = _$1561558287749;
        }

        public List<_$1561558287952Bean> get_$1561558287952() {
            return _$1561558287952;
        }

        public void set_$1561558287952(List<_$1561558287952Bean> _$1561558287952) {
            this._$1561558287952 = _$1561558287952;
        }

        public List<_$1561558288155Bean> get_$1561558288155() {
            return _$1561558288155;
        }

        public void set_$1561558288155(List<_$1561558288155Bean> _$1561558288155) {
            this._$1561558288155 = _$1561558288155;
        }

        public List<_$1561558288357Bean> get_$1561558288357() {
            return _$1561558288357;
        }

        public void set_$1561558288357(List<_$1561558288357Bean> _$1561558288357) {
            this._$1561558288357 = _$1561558288357;
        }

        public List<_$1561558299012Bean> get_$1561558299012() {
            return _$1561558299012;
        }

        public void set_$1561558299012(List<_$1561558299012Bean> _$1561558299012) {
            this._$1561558299012 = _$1561558299012;
        }

        public List<_$1561558304114Bean> get_$1561558304114() {
            return _$1561558304114;
        }

        public void set_$1561558304114(List<_$1561558304114Bean> _$1561558304114) {
            this._$1561558304114 = _$1561558304114;
        }

        public List<_$1561558307639Bean> get_$1561558307639() {
            return _$1561558307639;
        }

        public void set_$1561558307639(List<_$1561558307639Bean> _$1561558307639) {
            this._$1561558307639 = _$1561558307639;
        }

        public List<_$1561558377481Bean> get_$1561558377481() {
            return _$1561558377481;
        }

        public void set_$1561558377481(List<_$1561558377481Bean> _$1561558377481) {
            this._$1561558377481 = _$1561558377481;
        }

        public List<_$1561558380694Bean> get_$1561558380694() {
            return _$1561558380694;
        }

        public void set_$1561558380694(List<_$1561558380694Bean> _$1561558380694) {
            this._$1561558380694 = _$1561558380694;
        }

        public List<_$1561558390835Bean> get_$1561558390835() {
            return _$1561558390835;
        }

        public void set_$1561558390835(List<_$1561558390835Bean> _$1561558390835) {
            this._$1561558390835 = _$1561558390835;
        }

        public List<_$1561558398931Bean> get_$1561558398931() {
            return _$1561558398931;
        }

        public void set_$1561558398931(List<_$1561558398931Bean> _$1561558398931) {
            this._$1561558398931 = _$1561558398931;
        }

        public List<_$1561558400351Bean> get_$1561558400351() {
            return _$1561558400351;
        }

        public void set_$1561558400351(List<_$1561558400351Bean> _$1561558400351) {
            this._$1561558400351 = _$1561558400351;
        }

        public List<_$1561558403424Bean> get_$1561558403424() {
            return _$1561558403424;
        }

        public void set_$1561558403424(List<_$1561558403424Bean> _$1561558403424) {
            this._$1561558403424 = _$1561558403424;
        }

        public List<_$1561558403627Bean> get_$1561558403627() {
            return _$1561558403627;
        }

        public void set_$1561558403627(List<_$1561558403627Bean> _$1561558403627) {
            this._$1561558403627 = _$1561558403627;
        }

        public List<_$1561558403829Bean> get_$1561558403829() {
            return _$1561558403829;
        }

        public void set_$1561558403829(List<_$1561558403829Bean> _$1561558403829) {
            this._$1561558403829 = _$1561558403829;
        }

        public List<_$1561558403876Bean> get_$1561558403876() {
            return _$1561558403876;
        }

        public void set_$1561558403876(List<_$1561558403876Bean> _$1561558403876) {
            this._$1561558403876 = _$1561558403876;
        }

        public List<_$1561558405046Bean> get_$1561558405046() {
            return _$1561558405046;
        }

        public void set_$1561558405046(List<_$1561558405046Bean> _$1561558405046) {
            this._$1561558405046 = _$1561558405046;
        }

        public List<_$1561558405249Bean> get_$1561558405249() {
            return _$1561558405249;
        }

        public void set_$1561558405249(List<_$1561558405249Bean> _$1561558405249) {
            this._$1561558405249 = _$1561558405249;
        }

        public List<_$1561558405374Bean> get_$1561558405374() {
            return _$1561558405374;
        }

        public void set_$1561558405374(List<_$1561558405374Bean> _$1561558405374) {
            this._$1561558405374 = _$1561558405374;
        }

        public List<_$1561558407683Bean> get_$1561558407683() {
            return _$1561558407683;
        }

        public void set_$1561558407683(List<_$1561558407683Bean> _$1561558407683) {
            this._$1561558407683 = _$1561558407683;
        }

        public List<_$1561558408572Bean> get_$1561558408572() {
            return _$1561558408572;
        }

        public void set_$1561558408572(List<_$1561558408572Bean> _$1561558408572) {
            this._$1561558408572 = _$1561558408572;
        }

        public List<_$1561558408775Bean> get_$1561558408775() {
            return _$1561558408775;
        }

        public void set_$1561558408775(List<_$1561558408775Bean> _$1561558408775) {
            this._$1561558408775 = _$1561558408775;
        }

        public List<_$1561558408977Bean> get_$1561558408977() {
            return _$1561558408977;
        }

        public void set_$1561558408977(List<_$1561558408977Bean> _$1561558408977) {
            this._$1561558408977 = _$1561558408977;
        }

        public List<_$1561558410397Bean> get_$1561558410397() {
            return _$1561558410397;
        }

        public void set_$1561558410397(List<_$1561558410397Bean> _$1561558410397) {
            this._$1561558410397 = _$1561558410397;
        }

        public List<_$1561558410569Bean> get_$1561558410569() {
            return _$1561558410569;
        }

        public void set_$1561558410569(List<_$1561558410569Bean> _$1561558410569) {
            this._$1561558410569 = _$1561558410569;
        }

        public List<_$1561558411770Bean> get_$1561558411770() {
            return _$1561558411770;
        }

        public void set_$1561558411770(List<_$1561558411770Bean> _$1561558411770) {
            this._$1561558411770 = _$1561558411770;
        }

        public List<_$1561558411973Bean> get_$1561558411973() {
            return _$1561558411973;
        }

        public void set_$1561558411973(List<_$1561558411973Bean> _$1561558411973) {
            this._$1561558411973 = _$1561558411973;
        }

        public List<_$1561558412175Bean> get_$1561558412175() {
            return _$1561558412175;
        }

        public void set_$1561558412175(List<_$1561558412175Bean> _$1561558412175) {
            this._$1561558412175 = _$1561558412175;
        }

        public List<_$1561558412378Bean> get_$1561558412378() {
            return _$1561558412378;
        }

        public void set_$1561558412378(List<_$1561558412378Bean> _$1561558412378) {
            this._$1561558412378 = _$1561558412378;
        }

        public List<_$1561558412581Bean> get_$1561558412581() {
            return _$1561558412581;
        }

        public void set_$1561558412581(List<_$1561558412581Bean> _$1561558412581) {
            this._$1561558412581 = _$1561558412581;
        }

        public List<_$1561558413595Bean> get_$1561558413595() {
            return _$1561558413595;
        }

        public void set_$1561558413595(List<_$1561558413595Bean> _$1561558413595) {
            this._$1561558413595 = _$1561558413595;
        }

        public List<_$1561558433938Bean> get_$1561558433938() {
            return _$1561558433938;
        }

        public void set_$1561558433938(List<_$1561558433938Bean> _$1561558433938) {
            this._$1561558433938 = _$1561558433938;
        }

        public List<_$1561558439179Bean> get_$1561558439179() {
            return _$1561558439179;
        }

        public void set_$1561558439179(List<_$1561558439179Bean> _$1561558439179) {
            this._$1561558439179 = _$1561558439179;
        }

        public List<_$1561558444390Bean> get_$1561558444390() {
            return _$1561558444390;
        }

        public void set_$1561558444390(List<_$1561558444390Bean> _$1561558444390) {
            this._$1561558444390 = _$1561558444390;
        }

        public List<_$1561558449304Bean> get_$1561558449304() {
            return _$1561558449304;
        }

        public void set_$1561558449304(List<_$1561558449304Bean> _$1561558449304) {
            this._$1561558449304 = _$1561558449304;
        }

        public List<_$1561558460801Bean> get_$1561558460801() {
            return _$1561558460801;
        }

        public void set_$1561558460801(List<_$1561558460801Bean> _$1561558460801) {
            this._$1561558460801 = _$1561558460801;
        }

        public List<_$1561558461004Bean> get_$1561558461004() {
            return _$1561558461004;
        }

        public void set_$1561558461004(List<_$1561558461004Bean> _$1561558461004) {
            this._$1561558461004 = _$1561558461004;
        }

        public List<_$1561558461066Bean> get_$1561558461066() {
            return _$1561558461066;
        }

        public void set_$1561558461066(List<_$1561558461066Bean> _$1561558461066) {
            this._$1561558461066 = _$1561558461066;
        }

        public List<_$1561558462143Bean> get_$1561558462143() {
            return _$1561558462143;
        }

        public void set_$1561558462143(List<_$1561558462143Bean> _$1561558462143) {
            this._$1561558462143 = _$1561558462143;
        }

        public List<_$1561558467072Bean> get_$1561558467072() {
            return _$1561558467072;
        }

        public void set_$1561558467072(List<_$1561558467072Bean> _$1561558467072) {
            this._$1561558467072 = _$1561558467072;
        }

        public List<_$1561558467275Bean> get_$1561558467275() {
            return _$1561558467275;
        }

        public void set_$1561558467275(List<_$1561558467275Bean> _$1561558467275) {
            this._$1561558467275 = _$1561558467275;
        }

        public List<_$1561558467478Bean> get_$1561558467478() {
            return _$1561558467478;
        }

        public void set_$1561558467478(List<_$1561558467478Bean> _$1561558467478) {
            this._$1561558467478 = _$1561558467478;
        }

        public List<_$1561558467681Bean> get_$1561558467681() {
            return _$1561558467681;
        }

        public void set_$1561558467681(List<_$1561558467681Bean> _$1561558467681) {
            this._$1561558467681 = _$1561558467681;
        }

        public List<_$1561558476261Bean> get_$1561558476261() {
            return _$1561558476261;
        }

        public void set_$1561558476261(List<_$1561558476261Bean> _$1561558476261) {
            this._$1561558476261 = _$1561558476261;
        }

        public List<_$1561558483780Bean> get_$1561558483780() {
            return _$1561558483780;
        }

        public void set_$1561558483780(List<_$1561558483780Bean> _$1561558483780) {
            this._$1561558483780 = _$1561558483780;
        }

        public List<_$1561558485933Bean> get_$1561558485933() {
            return _$1561558485933;
        }

        public void set_$1561558485933(List<_$1561558485933Bean> _$1561558485933) {
            this._$1561558485933 = _$1561558485933;
        }

        public List<_$1561558486136Bean> get_$1561558486136() {
            return _$1561558486136;
        }

        public void set_$1561558486136(List<_$1561558486136Bean> _$1561558486136) {
            this._$1561558486136 = _$1561558486136;
        }

        public List<_$1561558487524Bean> get_$1561558487524() {
            return _$1561558487524;
        }

        public void set_$1561558487524(List<_$1561558487524Bean> _$1561558487524) {
            this._$1561558487524 = _$1561558487524;
        }

        public List<_$1561558487727Bean> get_$1561558487727() {
            return _$1561558487727;
        }

        public void set_$1561558487727(List<_$1561558487727Bean> _$1561558487727) {
            this._$1561558487727 = _$1561558487727;
        }

        public List<_$1561558492032Bean> get_$1561558492032() {
            return _$1561558492032;
        }

        public void set_$1561558492032(List<_$1561558492032Bean> _$1561558492032) {
            this._$1561558492032 = _$1561558492032;
        }

        public List<_$1561558514824Bean> get_$1561558514824() {
            return _$1561558514824;
        }

        public void set_$1561558514824(List<_$1561558514824Bean> _$1561558514824) {
            this._$1561558514824 = _$1561558514824;
        }

        public List<_$1561558517476Bean> get_$1561558517476() {
            return _$1561558517476;
        }

        public void set_$1561558517476(List<_$1561558517476Bean> _$1561558517476) {
            this._$1561558517476 = _$1561558517476;
        }

        public List<_$1561558517679Bean> get_$1561558517679() {
            return _$1561558517679;
        }

        public void set_$1561558517679(List<_$1561558517679Bean> _$1561558517679) {
            this._$1561558517679 = _$1561558517679;
        }

        public List<_$1561558519426Bean> get_$1561558519426() {
            return _$1561558519426;
        }

        public void set_$1561558519426(List<_$1561558519426Bean> _$1561558519426) {
            this._$1561558519426 = _$1561558519426;
        }

        public List<_$1561558520627Bean> get_$1561558520627() {
            return _$1561558520627;
        }

        public void set_$1561558520627(List<_$1561558520627Bean> _$1561558520627) {
            this._$1561558520627 = _$1561558520627;
        }

        public List<_$1561558520799Bean> get_$1561558520799() {
            return _$1561558520799;
        }

        public void set_$1561558520799(List<_$1561558520799Bean> _$1561558520799) {
            this._$1561558520799 = _$1561558520799;
        }

        public List<_$1561558521532Bean> get_$1561558521532() {
            return _$1561558521532;
        }

        public void set_$1561558521532(List<_$1561558521532Bean> _$1561558521532) {
            this._$1561558521532 = _$1561558521532;
        }

        public List<_$1561558526399Bean> get_$1561558526399() {
            return _$1561558526399;
        }

        public void set_$1561558526399(List<_$1561558526399Bean> _$1561558526399) {
            this._$1561558526399 = _$1561558526399;
        }

        public List<_$1561558530877Bean> get_$1561558530877() {
            return _$1561558530877;
        }

        public void set_$1561558530877(List<_$1561558530877Bean> _$1561558530877) {
            this._$1561558530877 = _$1561558530877;
        }

        public List<_$1561558535182Bean> get_$1561558535182() {
            return _$1561558535182;
        }

        public void set_$1561558535182(List<_$1561558535182Bean> _$1561558535182) {
            this._$1561558535182 = _$1561558535182;
        }

        public List<_$1561558541547Bean> get_$1561558541547() {
            return _$1561558541547;
        }

        public void set_$1561558541547(List<_$1561558541547Bean> _$1561558541547) {
            this._$1561558541547 = _$1561558541547;
        }

        public List<_$1561558541750Bean> get_$1561558541750() {
            return _$1561558541750;
        }

        public void set_$1561558541750(List<_$1561558541750Bean> _$1561558541750) {
            this._$1561558541750 = _$1561558541750;
        }

        public List<_$1561558541890Bean> get_$1561558541890() {
            return _$1561558541890;
        }

        public void set_$1561558541890(List<_$1561558541890Bean> _$1561558541890) {
            this._$1561558541890 = _$1561558541890;
        }

        public List<_$1561558542904Bean> get_$1561558542904() {
            return _$1561558542904;
        }

        public void set_$1561558542904(List<_$1561558542904Bean> _$1561558542904) {
            this._$1561558542904 = _$1561558542904;
        }

        public List<_$1561558543107Bean> get_$1561558543107() {
            return _$1561558543107;
        }

        public void set_$1561558543107(List<_$1561558543107Bean> _$1561558543107) {
            this._$1561558543107 = _$1561558543107;
        }

        public List<_$1561558543310Bean> get_$1561558543310() {
            return _$1561558543310;
        }

        public void set_$1561558543310(List<_$1561558543310Bean> _$1561558543310) {
            this._$1561558543310 = _$1561558543310;
        }

        public static class _$1Bean {
            /**
             * bytes : {"iPageId":1,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_ddbfc95ee4a66e8aaa1786179f7dc76c_2560_1440.jpg?sign=2743afe60049763aa52ad0c336495295&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558227392Bean {
            /**
             * bytes : {"iPageId":1,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_ddbfc95ee4a66e8aaa1786179f7dc76c_2560_1440.jpg?sign=2743afe60049763aa52ad0c336495295&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558227392,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558231029Bean {
            /**
             * bytes : {"iPageId":1,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_ddbfc95ee4a66e8aaa1786179f7dc76c_2560_1440.jpg?sign=2743afe60049763aa52ad0c336495295&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558231029,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558241230Bean {
            /**
             * bytes : {"iPageId":2,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_7605df8f065577f6ce23f1ec929c03cf_2560_1440.jpg?sign=0eff7abc1c0730edad8051f869c9485f&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558241230,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558254146Bean {
            /**
             * bytes : {"iPageId":3,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_a154985692764dc3a2657758ed0f09ee_2560_1440.jpg?sign=52910ad8e774501050afade36af6e7bb&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558254146,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558267875Bean {
            /**
             * bytes : {"version":1,"iType":5,"iCoursewareId":1561558218,"iPageId":3,"iId":1,"lSequence":1561558267875,"points":[{"x":7217,"y":3032},{"x":8175,"y":5039}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":200}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558283787Bean {
            /**
             * bytes : {"iPageId":4,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190508/1598_7ded3c72cb8fec44739d7f3f1e59f514_2560_1440.jpg?sign=7f65500cbb119ebfe9de393071438d07&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558283787,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558287141Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":4,"iId":2,"lSequence":1561558287141,"points":[{"x":3937,"y":6322},{"x":3937,"y":6392},{"x":3950,"y":6462}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":404}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558287343Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":4,"iId":2,"lSequence":1561558287343,"points":[{"x":3950,"y":6462},{"x":3963,"y":6485},{"x":3976,"y":6532},{"x":3989,"y":6532},{"x":3989,"y":6579},{"x":4015,"y":6579}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":203}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558287749Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":4,"iId":2,"lSequence":1561558287749,"points":[{"x":4015,"y":6579},{"x":4015,"y":6602},{"x":4041,"y":6579},{"x":4055,"y":6579},{"x":4055,"y":6555},{"x":4068,"y":6555},{"x":4068,"y":6532}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":404}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558287952Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":4,"iId":2,"lSequence":1561558287952,"points":[{"x":4068,"y":6532},{"x":4081,"y":6509},{"x":4094,"y":6509}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":202}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558288155Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":4,"iId":2,"lSequence":1561558288155,"points":[{"x":4081,"y":6509},{"x":4120,"y":6509},{"x":4133,"y":6485},{"x":4133,"y":6462},{"x":4146,"y":6439},{"x":4160,"y":6392},{"x":4173,"y":6369},{"x":4186,"y":6345},{"x":4212,"y":6322},{"x":4212,"y":6299}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":204}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558288357Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":4,"iId":2,"lSequence":1561558288357,"points":[{"x":4212,"y":6299},{"x":4225,"y":6299}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":204}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558299012Bean {
            /**
             * bytes : {"iPageId":7,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_0f62ba444ad3e4e8b4aa7d5b4823f3dd_2560_1440.jpg?sign=2d65aabea86556298a4bd1158e98d171&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558299012,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558304114Bean {
            /**
             * bytes : {"iPageId":8,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_2faea2c379bf9233287adfaf80829b38_2560_1440.jpg?sign=288b5452029ed7203a593973bca6f545&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558304114,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558307639Bean {
            /**
             * bytes : {"iPageId":9,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_9aacb699046c3bef7c26afd9c31577f8_2560_1440.jpg?sign=9d5fb15723af3613bc0003d8af9b3fd7&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558307639,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558377481Bean {
            /**
             * bytes : {"iPageId":10,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_ed567872a3d48a112b8a7bd98a2f58a2_2560_1440.jpg?sign=bf23eac303e1575703afdd560ca697f7&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558377481,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558380694Bean {
            /**
             * bytes : {"iPageId":11,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_fc8b2a269cf8ab4b561cf397a25a8257_2560_1440.jpg?sign=0c503d0617e968d8186502e5a7cc17e9&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558380694,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558390835Bean {
            /**
             * bytes : {"iPageId":12,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_09da44adccb6f7384e129dc8c49da8c3_2560_1440.jpg?sign=3326769c625bf383b078cf6ad89aea08&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558390835,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558398931Bean {
            /**
             * bytes : {"iPageId":13,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_985993fdde3df731d86320a4e4a96862_2560_1440.jpg?sign=f0d48508bc70bfd0d65f257796541c94&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558398931,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558400351Bean {
            /**
             * bytes : {"iPageId":14,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_e1107d07c98d7b3e7d015f95464a0dc6_2560_1440.jpg?sign=96d8ae97dc3d431de832b9c647b44b8b&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558400351,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558403424Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":3,"lSequence":1561558403424,"points":[{"x":4120,"y":4852},{"x":4225,"y":4852}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":198}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558403627Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":3,"lSequence":1561558403627,"points":[{"x":4120,"y":4852},{"x":4265,"y":4852},{"x":4304,"y":4806},{"x":4422,"y":4806}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":202}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558403829Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":3,"lSequence":1561558403829,"points":[{"x":4304,"y":4806},{"x":4475,"y":4806},{"x":4488,"y":4829}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":204}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558403876Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":3,"lSequence":1561558403876,"points":[{"x":4488,"y":4829},{"x":4514,"y":4829}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":51}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558405046Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":4,"lSequence":1561558405046,"points":[{"x":5091,"y":4899},{"x":5288,"y":4899}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":210}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558405249Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":4,"lSequence":1561558405249,"points":[{"x":5091,"y":4899},{"x":5524,"y":4899},{"x":5564,"y":4922},{"x":5629,"y":4922}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":194}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558405374Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":4,"lSequence":1561558405374,"points":[{"x":5629,"y":4922},{"x":5643,"y":4946},{"x":5669,"y":4946}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":136}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558407683Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":5,"lSequence":1561558407683,"points":[{"x":4488,"y":8165},{"x":4501,"y":8188},{"x":4724,"y":8188},{"x":4908,"y":8212},{"x":5000,"y":8305},{"x":5026,"y":8305}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":200}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558408572Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":6,"lSequence":1561558408572,"points":[{"x":5354,"y":8352},{"x":5564,"y":8352}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":201}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558408775Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":6,"lSequence":1561558408775,"points":[{"x":5354,"y":8352},{"x":5682,"y":8352}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":201}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558408977Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":6,"lSequence":1561558408977,"points":[{"x":5354,"y":8352},{"x":5761,"y":8352}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":200}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558410397Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":7,"lSequence":1561558410397,"points":[{"x":6049,"y":8282},{"x":6207,"y":8282},{"x":6430,"y":8328}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":193}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558410569Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":7,"lSequence":1561558410569,"points":[{"x":6430,"y":8328},{"x":6601,"y":8375},{"x":6614,"y":8375}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":182}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558411770Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":8,"lSequence":1561558411770,"points":[{"x":5551,"y":7022},{"x":5524,"y":7022},{"x":5498,"y":7045},{"x":5446,"y":7115},{"x":5419,"y":7185},{"x":5393,"y":7279},{"x":5393,"y":7302},{"x":5380,"y":7372},{"x":5380,"y":7395},{"x":5367,"y":7442}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":193}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558411973Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":8,"lSequence":1561558411973,"points":[{"x":5380,"y":7395},{"x":5354,"y":7489},{"x":5472,"y":7489}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":209}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558412175Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":8,"lSequence":1561558412175,"points":[{"x":5354,"y":7489},{"x":5485,"y":7489},{"x":5511,"y":7465},{"x":5524,"y":7465},{"x":5551,"y":7442},{"x":5616,"y":7442}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":199}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558412378Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":8,"lSequence":1561558412378,"points":[{"x":5616,"y":7442},{"x":5616,"y":7139}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":200}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558412581Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":14,"iId":8,"lSequence":1561558412581,"points":[{"x":5616,"y":7442},{"x":5616,"y":6929}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":201}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558413595Bean {
            /**
             * bytes : {"iPageId":15,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_e85e9dbfe858399755b0d4911e610df0_2560_1440.jpg?sign=2b6619b468838b624ccae052d71063c1&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558413595,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558433938Bean {
            /**
             * bytes : {"iPageId":16,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_5cc4f45bfcc3ec32e47b4a3eec3e3b28_2560_1440.jpg?sign=92d454ab1dad0c945525c942c1b3eec4&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558433938,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558439179Bean {
            /**
             * bytes : {"iPageId":17,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_1b3c3f7dff44113f0dea998b95796cd9_2560_1440.jpg?sign=bb5efac3b381ebaac78e9ad9d2212584&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558439179,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558444390Bean {
            /**
             * bytes : {"iPageId":18,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_ec2c586c5e4821a35da5cafc7267d849_2560_1440.jpg?sign=7bf8f0374ebf1bbe4da9fdd8d1c0c011&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558444390,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558449304Bean {
            /**
             * bytes : {"iPageId":19,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_7d9c04cad8f6e5d1ac38d88eaf54a348_2560_1440.jpg?sign=04974addf160ff7f6a9c170c60b778dc&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558449304,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558460801Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":19,"iId":9,"lSequence":1561558460801,"points":[{"x":5183,"y":5272},{"x":5209,"y":5272},{"x":5367,"y":5202},{"x":5485,"y":5202}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":203}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558461004Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":19,"iId":9,"lSequence":1561558461004,"points":[{"x":5485,"y":5202},{"x":5551,"y":5179},{"x":5577,"y":5156},{"x":5590,"y":5156}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":213}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558461066Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":19,"iId":9,"lSequence":1561558461066,"points":[{"x":5577,"y":5156},{"x":5616,"y":5156}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":52}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558462143Bean {
            /**
             * bytes : {"iPageId":20,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_281b7eb74f221fadf498b0b205b752df_2560_1440.jpg?sign=feb4e30fdd590281f4eb0839da6d8fc2&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558462143,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558467072Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":20,"iId":10,"lSequence":1561558467072,"points":[{"x":4593,"y":5762},{"x":4593,"y":5972},{"x":4606,"y":6042},{"x":4645,"y":6089},{"x":4645,"y":6182},{"x":4658,"y":6205},{"x":4658,"y":6299},{"x":4671,"y":6322},{"x":4671,"y":6369},{"x":4685,"y":6439},{"x":4698,"y":6485}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":189}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558467275Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":20,"iId":10,"lSequence":1561558467275,"points":[{"x":4698,"y":6485},{"x":4698,"y":6509},{"x":4711,"y":6555},{"x":4711,"y":6672},{"x":4737,"y":6695},{"x":4737,"y":6835},{"x":4750,"y":6859},{"x":4750,"y":6929},{"x":4763,"y":6975},{"x":4763,"y":7069},{"x":4776,"y":7092},{"x":4776,"y":7209}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":209}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558467478Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":20,"iId":10,"lSequence":1561558467478,"points":[{"x":4776,"y":7092},{"x":4776,"y":7535},{"x":4803,"y":7699},{"x":4803,"y":8445}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":200}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558467681Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":20,"iId":10,"lSequence":1561558467681,"points":[{"x":4803,"y":7699},{"x":4803,"y":8678},{"x":4829,"y":8772},{"x":4842,"y":8888},{"x":4842,"y":9005},{"x":4855,"y":9052},{"x":4855,"y":9075}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":201}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558476261Bean {
            /**
             * bytes : {"iPageId":21,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_5a5eb8ec39034a749be89ae9a2dd9208_2560_1440.jpg?sign=04618922a70c41548203a6a69a6f1144&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558476261,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558483780Bean {
            /**
             * bytes : {"iPageId":22,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_6f8e9b611564b2fabbdf987cbb967785_2560_1440.jpg?sign=6add789431a4201906eebc7177e97b49&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558483780,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558485933Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":22,"iId":11,"lSequence":1561558485933,"points":[{"x":6181,"y":5739},{"x":6181,"y":5785},{"x":6167,"y":5809},{"x":6154,"y":5879},{"x":6141,"y":5902},{"x":6102,"y":5972},{"x":6089,"y":6019},{"x":6076,"y":6019},{"x":6076,"y":6112},{"x":6062,"y":6182},{"x":6062,"y":6252}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":202}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558486136Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":22,"iId":11,"lSequence":1561558486136,"points":[{"x":6062,"y":6252},{"x":6036,"y":6299},{"x":6036,"y":6415},{"x":6023,"y":6439},{"x":6010,"y":6485},{"x":6010,"y":6579},{"x":5997,"y":6602},{"x":5997,"y":6649},{"x":5984,"y":6672},{"x":5984,"y":6719}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":203}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558487524Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":22,"iId":12,"lSequence":1561558487524,"points":[{"x":5656,"y":6789},{"x":5643,"y":6812},{"x":5616,"y":6905},{"x":5590,"y":7045},{"x":5577,"y":7232},{"x":5538,"y":7372},{"x":5538,"y":7629}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":199}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558487727Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":22,"iId":12,"lSequence":1561558487727,"points":[{"x":5538,"y":7629},{"x":5524,"y":7675},{"x":5524,"y":7722}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":195}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558492032Bean {
            /**
             * bytes : {"iPageId":23,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_68a645b7ba9d2206877b3393500554ee_2560_1440.jpg?sign=ecfa2833eab6a1fd5f5837841ff73e0e&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558492032,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558514824Bean {
            /**
             * bytes : {"iPageId":24,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_c6a4d431d261039fdf1cde4e657d6329_2560_1440.jpg?sign=e72c28f84e1d821e8f0431d97463f135&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558514824,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558517476Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":24,"iId":13,"lSequence":1561558517476,"points":[{"x":5695,"y":5902},{"x":5656,"y":5902},{"x":5643,"y":5925},{"x":5616,"y":5925},{"x":5603,"y":5949},{"x":5551,"y":6042},{"x":5551,"y":6112},{"x":5524,"y":6182},{"x":5511,"y":6229},{"x":5498,"y":6275},{"x":5498,"y":6369},{"x":5485,"y":6415},{"x":5485,"y":6439},{"x":5472,"y":6462},{"x":5472,"y":6485}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":194}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558517679Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":24,"iId":13,"lSequence":1561558517679,"points":[{"x":5472,"y":6462},{"x":5472,"y":6672}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":202}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558519426Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":24,"iId":14,"lSequence":1561558519426,"points":[{"x":5616,"y":6975},{"x":5616,"y":7045},{"x":5564,"y":7185},{"x":5564,"y":7232},{"x":5551,"y":7325},{"x":5551,"y":7442},{"x":5511,"y":7675},{"x":5498,"y":7722},{"x":5498,"y":7839},{"x":5485,"y":7862}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":401}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558520627Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":24,"iId":15,"lSequence":1561558520627,"points":[{"x":5590,"y":7722},{"x":5603,"y":7722},{"x":5603,"y":7769},{"x":5590,"y":7909},{"x":5577,"y":8025},{"x":5551,"y":8118},{"x":5551,"y":8188},{"x":5538,"y":8235},{"x":5511,"y":8258},{"x":5511,"y":8282}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":206}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558520799Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":24,"iId":15,"lSequence":1561558520799,"points":[{"x":5511,"y":8258},{"x":5511,"y":8328}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":167}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558521532Bean {
            /**
             * bytes : {"iPageId":25,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_31324aca39906037b4888f1afedd2c4a_2560_1440.jpg?sign=42fb4ab8510261bc80fca9366a5f1a2a&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558521532,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558526399Bean {
            /**
             * bytes : {"iPageId":26,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_076b5f737f144c4395fe00ef4634d361_2560_1440.jpg?sign=738e9a64971285ecfc7e6850849bcd01&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558526399,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558530877Bean {
            /**
             * bytes : {"iPageId":31,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_5f2660f5c001760ee4c15de3dee859c0_2560_1440.jpg?sign=459047f2597360157eb8b4fed3ada94f&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558530877,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558535182Bean {
            /**
             * bytes : {"iPageId":38,"sUrl":"//sfs-private.shangdejigou.cn/SunliveDocument/20190527/3461_6765ffdbc7aff622916d1f1feb20b118_2560_1440.jpg?sign=dd7a60de2e9bf3d5524434181a255014&t=1586717940","iType":1,"traces":{},"iWidth":2560,"iHeight":1440,"lSequence":1561558535182,"iCoursewareId":1561558218,"iTabulaWidth":16,"iTabulaHeight":9,"iScrollPosition":50}
             * eType : 10008
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558541547Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":38,"iId":16,"lSequence":1561558541547,"points":[{"x":7034,"y":4969},{"x":7099,"y":4969},{"x":7139,"y":4992},{"x":7362,"y":4992}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":203}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558541750Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":38,"iId":16,"lSequence":1561558541750,"points":[{"x":7139,"y":4992},{"x":7664,"y":4992},{"x":7821,"y":5062},{"x":7887,"y":5086},{"x":8005,"y":5109},{"x":8097,"y":5132},{"x":8123,"y":5132}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":200}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558541890Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":38,"iId":16,"lSequence":1561558541890,"points":[{"x":8097,"y":5132},{"x":8136,"y":5132}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":148}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558542904Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":38,"iId":17,"lSequence":1561558542904,"points":[{"x":7060,"y":9028},{"x":7598,"y":9028},{"x":7769,"y":9052},{"x":7900,"y":9075},{"x":8005,"y":9075},{"x":8031,"y":9098}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":203}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558543107Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":38,"iId":17,"lSequence":1561558543107,"points":[{"x":8031,"y":9098},{"x":8871,"y":9098}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":201}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }

        public static class _$1561558543310Bean {
            /**
             * bytes : {"version":1,"iType":1,"iCoursewareId":1561558218,"iPageId":38,"iId":17,"lSequence":1561558543310,"points":[{"x":8031,"y":9098},{"x":9146,"y":9098}],"penWidth":6,"iColor":4294901760,"sText":"","iDuration":204}
             * eType : 10006
             */

            private String bytes;
            private String eType;

            public String getBytes() {
                return bytes;
            }

            public void setBytes(String bytes) {
                this.bytes = bytes;
            }

            public String getEType() {
                return eType;
            }

            public void setEType(String eType) {
                this.eType = eType;
            }
        }
    }

    public static class VideoPlayUrlsBean {
        /**
         * bIsConcat : false
         * eCdnType : 1
         * eMediaType : 1
         * iBitrate : 0
         * iDuration : 344.1
         * lFileSize : 12495803
         * lSequence : 1561558230450
         * sFileName : 5285890793612291017
         * sFormat : mp4
         * sHttpsUrl : https://1257236654.vod2.myqcloud.com/cc26eeabvodcq1257236654/130b772a5285890793612291017/f0.mp4?t=5e8a2a74&us=d923fa75&sign=d5e15b0eb7a7f9e61005f90cf5e9158a
         * sUrl : http://1257236654.vod2.myqcloud.com/cc26eeabvodcq1257236654/130b772a5285890793612291017/f0.mp4?t=5e8a2a74&us=d923fa75&sign=d5e15b0eb7a7f9e61005f90cf5e9158a
         * sequenceInfos : [{"iDuration":344.1,"iStart":0,"lSequence":1561558230450}]
         * sharedPlaybackUrlInfos : []
         */

        private boolean bIsConcat;
        private String eCdnType;
        private String eMediaType;
        private String iBitrate;
        private double iDuration;
        private String lFileSize;
        private long lSequence;
        private String sFileName;
        private String sFormat;
        private String sHttpsUrl;
        private String sUrl;
        private List<SequenceInfosBean> sequenceInfos;
        private List<?> sharedPlaybackUrlInfos;

        public boolean isBIsConcat() {
            return bIsConcat;
        }

        public void setBIsConcat(boolean bIsConcat) {
            this.bIsConcat = bIsConcat;
        }

        public String getECdnType() {
            return eCdnType;
        }

        public void setECdnType(String eCdnType) {
            this.eCdnType = eCdnType;
        }

        public String getEMediaType() {
            return eMediaType;
        }

        public void setEMediaType(String eMediaType) {
            this.eMediaType = eMediaType;
        }

        public String getIBitrate() {
            return iBitrate;
        }

        public void setIBitrate(String iBitrate) {
            this.iBitrate = iBitrate;
        }

        public double getIDuration() {
            return iDuration;
        }

        public void setIDuration(double iDuration) {
            this.iDuration = iDuration;
        }

        public String getLFileSize() {
            return lFileSize;
        }

        public void setLFileSize(String lFileSize) {
            this.lFileSize = lFileSize;
        }

        public long getLSequence() {
            return lSequence;
        }

        public void setLSequence(long lSequence) {
            this.lSequence = lSequence;
        }

        public String getSFileName() {
            return sFileName;
        }

        public void setSFileName(String sFileName) {
            this.sFileName = sFileName;
        }

        public String getSFormat() {
            return sFormat;
        }

        public void setSFormat(String sFormat) {
            this.sFormat = sFormat;
        }

        public String getSHttpsUrl() {
            return sHttpsUrl;
        }

        public void setSHttpsUrl(String sHttpsUrl) {
            this.sHttpsUrl = sHttpsUrl;
        }

        public String getSUrl() {
            return sUrl;
        }

        public void setSUrl(String sUrl) {
            this.sUrl = sUrl;
        }

        public List<SequenceInfosBean> getSequenceInfos() {
            return sequenceInfos;
        }

        public void setSequenceInfos(List<SequenceInfosBean> sequenceInfos) {
            this.sequenceInfos = sequenceInfos;
        }

        public List<?> getSharedPlaybackUrlInfos() {
            return sharedPlaybackUrlInfos;
        }

        public void setSharedPlaybackUrlInfos(List<?> sharedPlaybackUrlInfos) {
            this.sharedPlaybackUrlInfos = sharedPlaybackUrlInfos;
        }

        public static class SequenceInfosBean {
            /**
             * iDuration : 344.1
             * iStart : 0
             * lSequence : 1561558230450
             */

            private double iDuration;
            private String iStart;
            private long lSequence;

            public double getIDuration() {
                return iDuration;
            }

            public void setIDuration(double iDuration) {
                this.iDuration = iDuration;
            }

            public String getIStart() {
                return iStart;
            }

            public void setIStart(String iStart) {
                this.iStart = iStart;
            }

            public long getLSequence() {
                return lSequence;
            }

            public void setLSequence(long lSequence) {
                this.lSequence = lSequence;
            }
        }
    }
}
