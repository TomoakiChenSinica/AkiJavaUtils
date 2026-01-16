package tw.dev.tomoaki.datafilesystem.core.helper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import tw.dev.tomoaki.datafilesystem.core.entity.DataFileRelation;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.*;

/**
 * DataFileRelationHelper 單元測試
 *
 * @author tomoaki
 */
@DisplayName("DataFileRelationHelper 測試")
class DataFileRelationHelperTest {

    /**
     * 測試用的 DataFileRelation 實現
     */
    private static class TestDataFile implements DataFileRelation<Object> {
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

    @Test
    @DisplayName("測試 obtainFilePath - 正常情況")
    void testObtainFilePath_Normal() {
        // Given
        String rootPath = "/var/data/files";
        TestDataFile dataFile = new TestDataFile("test-file.txt", "測試檔案.txt");

        // When
        Path result = DataFileRelationHelper.obtainFilePath(rootPath, dataFile);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(Paths.get(rootPath, "test-file.txt"));
    }

    @Test
    @DisplayName("測試 obtainFilePath - dataFile 為 null")
    void testObtainFilePath_NullDataFile() {
        // Given
        String rootPath = "/var/data/files";

        // When
        Path result = DataFileRelationHelper.obtainFilePath(rootPath, null);

        // Then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("測試 obtainFilePath - fileRealName 為 null")
    void testObtainFilePath_NullRealName() {
        // Given
        String rootPath = "/var/data/files";
        TestDataFile dataFile = new TestDataFile(null, "測試檔案.txt");

        // When
        Path result = DataFileRelationHelper.obtainFilePath(rootPath, dataFile);

        // Then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("測試 obtainFilePath - fileRealName 為空字串")
    void testObtainFilePath_EmptyRealName() {
        // Given
        String rootPath = "/var/data/files";
        TestDataFile dataFile = new TestDataFile("", "測試檔案.txt");

        // When
        Path result = DataFileRelationHelper.obtainFilePath(rootPath, dataFile);

        // Then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("測試 obtainFilePath - 包含子目錄")
    void testObtainFilePath_WithSubdirectory() {
        // Given
        String rootPath = "/var/data/files";
        TestDataFile dataFile = new TestDataFile("subfolder/test-file.txt", "測試檔案.txt");

        // When
        Path result = DataFileRelationHelper.obtainFilePath(rootPath, dataFile);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(Paths.get(rootPath, "subfolder/test-file.txt"));
    }

    @Test
    @DisplayName("測試 hasDataFile - dataFile 存在")
    void testHasDataFile_Exists() {
        // Given
        TestDataFile dataFile = new TestDataFile("test.txt", "測試.txt");

        // When
        Boolean result = DataFileRelationHelper.hasDataFile(dataFile);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("測試 hasDataFile - dataFile 為 null")
    void testHasDataFile_Null() {
        // When
        Boolean result = DataFileRelationHelper.hasDataFile(null);

        // Then
        assertThat(result).isFalse();
    }
}
