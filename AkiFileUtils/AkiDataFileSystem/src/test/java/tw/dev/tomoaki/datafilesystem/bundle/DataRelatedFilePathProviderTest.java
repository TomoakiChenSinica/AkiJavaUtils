package tw.dev.tomoaki.datafilesystem.bundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import tw.dev.tomoaki.datafilesystem.core.entity.DataFileRelation;
import tw.dev.tomoaki.filesystem.exception.FileAccessDeninedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;
import tw.dev.tomoaki.datafilesystem.core.DataFileNamingStrategy;

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
            super();
            this.fileRoot = fileRoot;
        }

        public TestPathProvider(String fileRoot, DataFileNamingStrategy<TestDataFile> fileCreator) {
            super(fileCreator);
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
        
        @Override
        protected Logger logger() {
            return Logger.getLogger(getClass().getName());
        }        
    }

    /**
     * 測試用的自訂 DataFileCreator - 使用固定前綴
     */
    private static class PrefixFileCreator implements DataFileNamingStrategy<TestDataFile> {
        private final String prefix;

        public PrefixFileCreator(String prefix) {
            this.prefix = prefix;
        }

        @Override
        public String createFileName(TestDataFile data) {
            return prefix + "-" + System.currentTimeMillis();
        }
    }

    /**
     * 測試用的自訂 DataFileCreator - 基於 displayName 生成
     */
    private static class DisplayNameBasedFileCreator implements DataFileNamingStrategy<TestDataFile> {
        @Override
        public String createFileName(TestDataFile data) {
            if (data == null || data.getFileDisplayName() == null) {
                return "unnamed";
            }
            // 取得不含副檔名的部分
            String displayName = data.getFileDisplayName();
            int dotIndex = displayName.lastIndexOf('.');
            if (dotIndex > 0) {
                return displayName.substring(0, dotIndex).replaceAll("[^a-zA-Z0-9]", "_");
            }
            return displayName.replaceAll("[^a-zA-Z0-9]", "_");
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

    // ========== 自訂 DataFileCreator 測試 ==========

    @Nested
    @DisplayName("自訂 DataFileCreator 測試")
    class CustomDataFileCreatorTest {

        @Test
        @DisplayName("使用自訂前綴 FileCreator")
        void testCustomPrefixFileCreator() {
            // Given
            String prefix = "custom";
            TestPathProvider customProvider = new TestPathProvider(
                    tempDir.toString(),
                    new PrefixFileCreator(prefix)
            );
            TestDataFile dataFile = new TestDataFile(null, "test.txt");
            testData.setFile(dataFile);

            // When
            String fileName = customProvider.createFileName(testData);

            // Then
            assertThat(fileName).startsWith(prefix + "-");
        }

        @Test
        @DisplayName("使用 DisplayName 為基礎的 FileCreator")
        void testDisplayNameBasedFileCreator() {
            // Given
            TestPathProvider customProvider = new TestPathProvider(
                    tempDir.toString(),
                    new DisplayNameBasedFileCreator()
            );
            TestDataFile dataFile = new TestDataFile(null, "我的文件.pdf");
            testData.setFile(dataFile);

            // When
            String fileName = customProvider.createFileName(testData);

            // Then
            assertThat(fileName).isEqualTo("____"); // 中文字元被替換為底線
        }

        @Test
        @DisplayName("使用 DisplayName 為基礎的 FileCreator - 英文檔名")
        void testDisplayNameBasedFileCreator_EnglishName() {
            // Given
            TestPathProvider customProvider = new TestPathProvider(
                    tempDir.toString(),
                    new DisplayNameBasedFileCreator()
            );
            TestDataFile dataFile = new TestDataFile(null, "MyDocument.pdf");
            testData.setFile(dataFile);

            // When
            String fileName = customProvider.createFileName(testData);

            // Then
            assertThat(fileName).isEqualTo("MyDocument");
        }

        @Test
        @DisplayName("自訂 FileCreator - obtainNewFilePath 使用自訂檔名")
        void testObtainNewFilePath_WithCustomFileCreator() {
            // Given
            String prefix = "article";
            TestPathProvider customProvider = new TestPathProvider(
                    tempDir.toString(),
                    new PrefixFileCreator(prefix)
            );
            TestDataFile dataFile = new TestDataFile(null, "test.txt");
            testData.setFile(dataFile);

            // When
            Path newPath = customProvider.obtainNewFilePath(testData);

            // Then
            assertThat(newPath).isNotNull();
            assertThat(newPath.getFileName().toString()).startsWith(prefix + "-");
            assertThat(newPath.getParent()).isEqualTo(tempDir);
        }

        @Test
        @DisplayName("不同 FileCreator 實例產生不同檔名")
        void testDifferentFileCreators_ProduceDifferentFileNames() {
            // Given
            TestPathProvider defaultProvider = new TestPathProvider(tempDir.toString());
            TestPathProvider prefixProvider = new TestPathProvider(
                    tempDir.toString(),
                    new PrefixFileCreator("prefix")
            );
            TestPathProvider displayNameProvider = new TestPathProvider(
                    tempDir.toString(),
                    new DisplayNameBasedFileCreator()
            );

            TestDataFile dataFile = new TestDataFile(null, "Document.txt");
            testData.setFile(dataFile);

            // When
            String defaultFileName = defaultProvider.createFileName(testData);
            String prefixFileName = prefixProvider.createFileName(testData);
            String displayNameFileName = displayNameProvider.createFileName(testData);

            // Then
            // 預設使用 UUID 格式
            assertThat(defaultFileName).matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
            // Prefix 開頭
            assertThat(prefixFileName).startsWith("prefix-");
            // DisplayName 為基礎
            assertThat(displayNameFileName).isEqualTo("Document");
        }
    }

    // ========== 帶副檔名 (Extension) 的測試 ==========

    
    @Nested
    @DisplayName("帶副檔名 createFileName 測試")
    class CreateFileNameWithExtensionTest {

        @Test
        @DisplayName("createFileName 帶 extension - 預設 UUID")
        void testCreateFileName_WithExtension_DefaultUUID() {
            // Given
            TestDataFile dataFile = new TestDataFile(null, "test.txt");
            testData.setFile(dataFile);

            // When
            String fileName = pathProvider.createFileName(testData, "jpg");

            // Then
            assertThat(fileName).endsWith(".jpg");
            // UUID 部分 + .jpg
            assertThat(fileName).matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}\\.jpg");
        }

        @Test
        @DisplayName("createFileName 帶 extension - 自訂 FileCreator")
        void testCreateFileName_WithExtension_CustomFileCreator() {
            // Given
            TestPathProvider customProvider = new TestPathProvider(
                    tempDir.toString(),
                    new DisplayNameBasedFileCreator()
            );
            TestDataFile dataFile = new TestDataFile(null, "MyPhoto.png");
            testData.setFile(dataFile);

            // When
            String fileName = customProvider.createFileName(testData, "jpg");

            // Then
            assertThat(fileName).isEqualTo("MyPhoto.jpg");
        }

        @Test
        @DisplayName("createFileName 帶 extension - 多種副檔名")
        void testCreateFileName_WithExtension_VariousExtensions() {
            // Given
            TestDataFile dataFile = new TestDataFile(null, "test.txt");
            testData.setFile(dataFile);

            // When & Then
            assertThat(pathProvider.createFileName(testData, "pdf")).endsWith(".pdf");
            assertThat(pathProvider.createFileName(testData, "docx")).endsWith(".docx");
            assertThat(pathProvider.createFileName(testData, "png")).endsWith(".png");
            assertThat(pathProvider.createFileName(testData, "tar.gz")).endsWith(".tar.gz");
        }

        @Test
        @DisplayName("createFileName 帶空 extension")
        void testCreateFileName_WithEmptyExtension() {
            // Given
            TestDataFile dataFile = new TestDataFile(null, "test.txt");
            testData.setFile(dataFile);

            // When
            String fileName = pathProvider.createFileName(testData, "");

            // Then
            // 應該是 UUID + "."
            assertThat(fileName).endsWith(".");
        }
    }
}
