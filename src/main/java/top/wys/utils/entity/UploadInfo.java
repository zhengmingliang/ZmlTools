/**
 * Created by 郑明亮 on 2020/9/5 16:00.
 */

//

package top.wys.utils.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <ol>
 *  <li>上传文件的相关信息
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 * @time 2020/9/5 16:00
 * @email mpro@vip.qq.com
 */
public class UploadInfo {
    /**
     * 上传文件时作为请求的key值
     */
    private String key = "file";
    /**
     * 上传文件的绝对路径
     */
    private String filePath;
    /**
     * 上传文件的文件名称
     */
    private String fileName;
    /**
     * 上传文件类型
     */
    private String mediaType;

    public UploadInfo(String filePath) {
        this.filePath = filePath;
    }

    public UploadInfo(String key, String filePath) {
        this.key = key;
        this.filePath = filePath;
    }

    public UploadInfo(String key, String filePath, String fileName) {
        this.key = key;
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
