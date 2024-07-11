package com.ohgiraffers.fileupload;

public class FileDTO {

    // 원본이름을 먼저 데이터베이스에 저장
    private String originFileName;

    // 실행파일의 확장자를 이용하여 해킹에 이용할수 있기떄문에 확장자를 분리하여 저장하기 위해 사용, 이름 중복을 피하기 위하여 이름을 랜덤하게 변환하여 저장
    private String saveName;

    // 저장경로
    private String filePath;

    // 파일의 설명
    private String fileDescription;

    public FileDTO() {
    }

    public FileDTO(String originFileName, String saveName, String filePath, String fileDescription) {
        this.originFileName = originFileName;
        this.saveName = saveName;
        this.filePath = filePath;
        this.fileDescription = fileDescription;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "originFileName='" + originFileName + '\'' +
                ", saveName='" + saveName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileDescription='" + fileDescription + '\'' +
                '}';
    }
}
