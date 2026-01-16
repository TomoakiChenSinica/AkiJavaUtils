package tw.dev.tomoaki.datafilesystem.bundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import tw.dev.tomoaki.datafilesystem.core.entity.DataFileRelation;
import tw.dev.tomoaki.filesystem.exception.FileAccessDeninedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

/**
 * DataRelatedFilePathProvider 單元測試
 *
 * @author tomoaki
 */
@DisplayName("DataRelatedFilePathProvider 測試")
class DataRelatedFilePathProviderTest {

    @TempDir
    Path tempDir;

    private TestPathProvider pathProvider;
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
        private String fileRoot;

        public TestPathProvider(String fileRoot) {
            this.fileRoot = fileRoot;
        }

        @Override
        protected String getFileRoot() {
            return fileRoot;
        }

        @Override
        protected TestDataFile obtainDataFile(TestData dataEntity) {
            return dataEntity.getFile();
        }
    }

    @BeforeEach
    void setUp() {
        pathProvider = new TestPathProvider(tempDir.toString());
        testData = new TestData(1L);
    }

    @Test
    @DisplayName("測試 obtainRecentFilePath - 正常情況")
    void testObtainRecentFilePath_Normal() {
        // Given
        TestDataFile dataFile = new TestDataFile("existing-file.txt", "既有檔案.txt");
        testData.setFile(dataFile);

        // When
        Path result = pathProvider.obtainRecentFilePath(testData);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getFileName().toString()).isEqualTo("existing-file.txt");
        assertThat(result.getParent()).isEqualTo(tempDir);
    }

    @Test
    @DisplayName("測試 obtainRecentFilePath - dataFile 為 null")
    void testObtainRecentFilePath_NullDataFile() {
        // Given
        testData.setFile(null);

        // When
        Path result = pathProvider.obtainRecentFilePath(testData);

        // Then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("測試 obtainRecentFilePath - 路徑遍歷攻擊防護")
    void testObtainRecentFilePath_PathTraversalAttack() {
        // Given
        TestDataFile maliciousFile = new TestDataFile("../../../etc/passwd", "惡意檔案");
        testData.setFile(maliciousFile);

        // When & Then
        assertThatThrownBy(() -> pathProvider.obtainRecentFilePath(testData))
                .isInstanceOf(FileAccessDeninedException.class);
    }

    @Test
    @DisplayName("測試 obtainNewFilePath - 生成新檔案路徑")
    void testObtainNewFilePath_Normal() {
        // When
        Path result = pathProvider.obtainNewFilePath(testData);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getParent()).isEqualTo(tempDir);
        assertThat(result.getFileName().toString()).isNotEmpty();
    }

    @Test
    @DisplayName("測試 createFileName - UUID 格式")
    void testCreateFileName_UUIDFormat() {
        // When
        String fileName1 = pathProvider.createFileName(testData);
        String fileName2 = pathProvider.createFileName(testData);

        // Then
        assertThat(fileName1).isNotEmpty();
        assertThat(fileName2).isNotEmpty();
        assertThat(fileName1).isNotEqualTo(fileName2); // 每次生成應該不同

        // UUID 格式檢查 (8-4-4-4-12)
        assertThat(fileName1).matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
    }

    @Test
    @DisplayName("測試 hasRecentFile - 有既有檔案")
    void testHasRecentFile_HasFile() {
        // Given
        TestDataFile dataFile = new TestDataFile("test.txt", "測試.txt");
        testData.setFile(dataFile);

        // When
        Boolean result = pathProvider.hasRecentFile(testData);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("測試 hasRecentFile - 無既有檔案")
    void testHasRecentFile_NoFile() {
        // Given
        testData.setFile(null);

        // When
        Boolean result = pathProvider.hasRecentFile(testData);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("測試 isRecentFileExists - 檔案存在")
    void testIsRecentFileExists_FileExists() throws IOException {
        // Given
        Path testFile = tempDir.resolve("existing.txt");
        Files.createFile(testFile);

        TestDataFile dataFile = new TestDataFile("existing.txt", "存在的檔案.txt");
        testData.setFile(dataFile);

        // When
        Boolean result = pathProvider.isRecentFileExists(testData);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("測試 isRecentFileExists - 檔案不存在")
    void testIsRecentFileExists_FileNotExists() {
        // Given
        TestDataFile dataFile = new TestDataFile("non-existing.txt", "不存在的檔案.txt");
        testData.setFile(dataFile);

        // When
        Boolean result = pathProvider.isRecentFileExists(testData);

        // Then
        assertThat(result).isFalse();
    }
}
