package tw.dev.tomoaki.datafilesystem.bundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import tw.dev.tomoaki.datafilesystem.core.entity.DataFileRelation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

/**
 * DataRelatedFileManager 單元測試
 *
 * @author tomoaki
 */
@DisplayName("DataRelatedFileManager 測試")
class DataRelatedFileManagerTest {

    @TempDir
    Path tempDir;

    private TestFileManager fileManager;
    private TestData testData;

    /**
     * 測試用的主資料類別
     */
    private static class TestData {
        private Long id;
        private TestDataFile file;

        public TestData(Long id) {
            this.id = id;
        }

        public void setFile(TestDataFile file) {
            this.file = file;
        }

        public TestDataFile getFile() {
            return file;
        }

        public Long getId() {
            return id;
        }
    }

    /**
     * 測試用的檔案資料類別
     */
    private static class TestDataFile implements DataFileRelation<TestData> {
        private String realName;
        private String displayName;

        public TestDataFile(String realName, String displayName) {
            this.realName = realName;
            this.displayName = displayName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        @Override
        public String getFileRealName() {
            return realName;
        }

        @Override
        public String getFileDisplayName() {
            return displayName;
        }
    }

    /**
     * 測試用的 PathProvider 實現
     */
    private class TestPathProvider extends DataRelatedFilePathProvider<TestData, TestDataFile> {
        @Override
        protected String getFileRoot() {
            return tempDir.toString();
        }

        @Override
        protected TestDataFile obtainDataFile(TestData dataEntity) {
            return dataEntity.getFile();
        }
    }

    /**
     * 測試用的 FileManager 實現
     */
    private class TestFileManager extends DataRelatedFileManager<TestData, TestPathProvider> {
        private TestPathProvider pathProvider;

        public TestFileManager(TestPathProvider pathProvider) {
            this.pathProvider = pathProvider;
        }

        @Override
        protected TestPathProvider obtainPathProvider() {
            return pathProvider;
        }

        @Override
        protected String obtainFileNotFoundMsg(TestData data) {
            return "找不到 ID=" + data.getId() + " 的檔案";
        }
    }

    @BeforeEach
    void setUp() {
        TestPathProvider pathProvider = new TestPathProvider();
        fileManager = new TestFileManager(pathProvider);
        testData = new TestData(1L);
    }

    @Test
    @DisplayName("測試 save - 儲存新檔案")
    void testSave_NewFile() throws IOException {
        // Given
        String content = "測試內容";
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());

        // When
        File savedFile = fileManager.save(testData, inputStream, false);

        // Then
        assertThat(savedFile).exists();
        assertThat(savedFile.getParent()).isEqualTo(tempDir.toString());

        // 驗證內容
        String savedContent = new String(Files.readAllBytes(savedFile.toPath()));
        assertThat(savedContent).isEqualTo(content);
    }

    @Test
    @DisplayName("測試 save - 覆寫既有檔案")
    void testSave_OverwriteExistingFile() throws IOException {
        // Given - 先創建一個既有檔案
        Path existingFile = tempDir.resolve("existing.txt");
        Files.write(existingFile, "舊內容".getBytes());

        TestDataFile dataFile = new TestDataFile("existing.txt", "既有檔案.txt");
        testData.setFile(dataFile);

        String newContent = "新內容";
        InputStream inputStream = new ByteArrayInputStream(newContent.getBytes());

        // When - 以覆寫模式儲存
        File savedFile = fileManager.save(testData, inputStream, true);

        // Then
        assertThat(savedFile).exists();
        assertThat(savedFile.toPath()).isEqualTo(existingFile);

        // 驗證內容已被覆寫
        String savedContent = new String(Files.readAllBytes(savedFile.toPath()));
        assertThat(savedContent).isEqualTo(newContent);
    }

    @Test
    @DisplayName("測試 save - 不覆寫既有檔案")
    void testSave_NoOverwriteExistingFile() throws IOException {
        // Given - 先創建一個既有檔案
        Path existingFile = tempDir.resolve("existing.txt");
        Files.write(existingFile, "舊內容".getBytes());

        TestDataFile dataFile = new TestDataFile("existing.txt", "既有檔案.txt");
        testData.setFile(dataFile);

        String newContent = "新內容";
        InputStream inputStream = new ByteArrayInputStream(newContent.getBytes());

        // When - 不覆寫，應該建立新檔案
        File savedFile = fileManager.save(testData, inputStream, false);

        // Then
        assertThat(savedFile).exists();
        assertThat(savedFile.toPath()).isNotEqualTo(existingFile); // 應該是不同的檔案

        // 驗證舊檔案內容未變
        String oldContent = new String(Files.readAllBytes(existingFile));
        assertThat(oldContent).isEqualTo("舊內容");

        // 驗證新檔案內容
        String savedContent = new String(Files.readAllBytes(savedFile.toPath()));
        assertThat(savedContent).isEqualTo(newContent);
    }

    @Test
    @DisplayName("測試 obtainSaveFilePath - 覆寫模式且有既有檔案")
    void testObtainSaveFilePath_OverwriteWithExisting() throws IOException {
        // Given
        Path existingFile = tempDir.resolve("existing.txt");
        Files.createFile(existingFile);

        TestDataFile dataFile = new TestDataFile("existing.txt", "既有檔案.txt");
        testData.setFile(dataFile);

        // When
        Path savePath = fileManager.obtainSaveFilePath(testData, true);

        // Then
        assertThat(savePath).isEqualTo(existingFile);
    }

    @Test
    @DisplayName("測試 obtainSaveFilePath - 不覆寫模式")
    void testObtainSaveFilePath_NoOverwrite() throws IOException {
        // Given
        Path existingFile = tempDir.resolve("existing.txt");
        Files.createFile(existingFile);

        TestDataFile dataFile = new TestDataFile("existing.txt", "既有檔案.txt");
        testData.setFile(dataFile);

        // When
        Path savePath = fileManager.obtainSaveFilePath(testData, false);

        // Then
        assertThat(savePath).isNotNull();
        assertThat(savePath).isNotEqualTo(existingFile); // 應該產生新路徑
    }

    @Test
    @DisplayName("測試 delete - 刪除既有檔案")
    void testDelete_ExistingFile() throws IOException {
        // Given
        Path existingFile = tempDir.resolve("to-delete.txt");
        Files.write(existingFile, "要刪除的內容".getBytes());

        TestDataFile dataFile = new TestDataFile("to-delete.txt", "要刪除.txt");
        testData.setFile(dataFile);

        // When
        File deletedFile = fileManager.delete(testData);

        // Then
        assertThat(deletedFile.toPath()).isEqualTo(existingFile);
        assertThat(deletedFile).doesNotExist(); // 檔案應該已被刪除
    }

    @Test
    @DisplayName("測試 delete - 檔案不存在時拋出例外")
    void testDelete_FileNotFound() {
        // Given
        TestDataFile dataFile = new TestDataFile("non-existing.txt", "不存在.txt");
        testData.setFile(dataFile);

        // When & Then
        assertThatThrownBy(() -> fileManager.delete(testData))
                .isInstanceOf(FileNotFoundException.class)
                .hasMessageContaining("找不到 ID=1 的檔案");
    }

    @Test
    @DisplayName("測試 delete - dataFile 為 null 時拋出例外")
    void testDelete_NullDataFile() {
        // Given
        testData.setFile(null);

        // When & Then
        // 當 dataFile 為 null 時，obtainRecentFilePath 返回 null
        // path.toFile() 會拋出 NullPointerException
        assertThatThrownBy(() -> fileManager.delete(testData))
                .isInstanceOf(NullPointerException.class);
    }
}
