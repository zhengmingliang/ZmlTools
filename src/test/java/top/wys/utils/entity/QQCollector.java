package top.wys.utils.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class QQCollector {
    @JSONField(name = "shareData")
    private ShareDataDTO shareData;
    @JSONField(name = "userInfo")
    private UserInfoDTO userInfo;

    @NoArgsConstructor
    @Data
    public static class ShareDataDTO {
        @JSONField(name = "pdir_key")
        private String pdirKey;
        @JSONField(name = "collection")
        private CollectionDTO collection;
        @JSONField(name = "group_code")
        private Integer groupCode;
        @JSONField(name = "create_time")
        private Integer createTime;
        @JSONField(name = "down_cnt")
        private Integer downCnt;
        @JSONField(name = "is_editable")
        private Boolean isEditable;
        @JSONField(name = "share_head_image_url")
        private String shareHeadImageUrl;
        @JSONField(name = "view_cnt")
        private Integer viewCnt;
        @JSONField(name = "res_type")
        private Integer resType;

        @NoArgsConstructor
        @Data
        public static class CollectionDTO {
            @JSONField(name = "cid")
            private String cid;
            @JSONField(name = "status")
            private Integer status;
            @JSONField(name = "type")
            private Integer type;
            @JSONField(name = "author")
            private AuthorDTO author;
            @JSONField(name = "summary")
            private SummaryDTO summary;
            @JSONField(name = "modify_time")
            private Long modifyTime;
            @JSONField(name = "bid")
            private Integer bid;
            @JSONField(name = "src_app_id")
            private Integer srcAppId;
            @JSONField(name = "src_app_ver")
            private String srcAppVer;
            @JSONField(name = "category")
            private Integer category;
            @JSONField(name = "create_time")
            private Long createTime;
            @JSONField(name = "collect_time")
            private Long collectTime;
            @JSONField(name = "sequence")
            private Long sequence;

            @NoArgsConstructor
            @Data
            public static class AuthorDTO {
                @JSONField(name = "type")
                private Integer type;
                @JSONField(name = "num_id")
                private Integer numId;
                @JSONField(name = "str_id")
                private String strId;
                @JSONField(name = "group_id")
                private Integer groupId;
                @JSONField(name = "group_name")
                private String groupName;
            }

            @NoArgsConstructor
            @Data
            public static class SummaryDTO {
                @JSONField(name = "rich_media_summary")
                private RichMediaSummaryDTO richMediaSummary;

                @NoArgsConstructor
                @Data
                public static class RichMediaSummaryDTO {
                    @JSONField(name = "title")
                    private String title;
                    @JSONField(name = "brief")
                    private String brief;
                    @JSONField(name = "pic_list")
                    private List<PicListDTO> picList;
                    @JSONField(name = "content_type")
                    private Integer contentType;

                    @NoArgsConstructor
                    @Data
                    public static class PicListDTO {
                        @JSONField(name = "uri")
                        private String uri;
                        @JSONField(name = "owner")
                        private OwnerDTO owner;
                        @JSONField(name = "md5")
                        private String md5;
                        @JSONField(name = "height")
                        private Integer height;
                        @JSONField(name = "size")
                        private Integer size;
                        @JSONField(name = "type")
                        private Integer type;
                        @JSONField(name = "name")
                        private String name;
                        @JSONField(name = "width")
                        private Integer width;
                        @JSONField(name = "pic_id")
                        private String picId;

                        @NoArgsConstructor
                        @Data
                        public static class OwnerDTO {
                            @JSONField(name = "type")
                            private Integer type;
                            @JSONField(name = "num_id")
                            private Integer numId;
                            @JSONField(name = "group_id")
                            private Integer groupId;
                        }
                    }
                }
            }
        }

        @NoArgsConstructor
        @Data
        public static class ResourceListDTO {
            @NoArgsConstructor
            @Data
            public static class PicInfoDTO {
            }
        }
    }

    @NoArgsConstructor
    @Data
    public static class UserInfoDTO {
    }
}
