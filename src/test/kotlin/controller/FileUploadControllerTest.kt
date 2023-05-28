package controller

import com.example.pdftohtmlKotlin.PdfToHtmlKotlinApplication
import com.example.pdftohtmlKotlin.application.ConvertPdfUseCase
import com.example.pdftohtmlKotlin.domain.HtmlFile
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.boot.test.mock.mockito.MockBean
import java.io.File

@SpringBootTest(classes = [PdfToHtmlKotlinApplication::class])
@AutoConfigureMockMvc
class FileUploadControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var convertPdfUseCase: ConvertPdfUseCase

    @BeforeEach
    fun setup() {
        val htmlFile = HtmlFile("", "/html/test.html")
        whenever(convertPdfUseCase.execute(any())).thenReturn(htmlFile)
    }

    @Test
    fun `it should receive file and convert to html`() {
        // given
        val classLoader = FileUploadControllerTest::class.java.classLoader
        val file = File(classLoader.getResource("test.pdf").file)
        val pdfFile = MockMultipartFile(
            "file",
            "test.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            file.readBytes()
        )

        // when
        mockMvc.perform(multipart("/upload").file(pdfFile))
            .andExpect(status().isOk)
            .andExpect(content().string("/html/test.html"))

        // then
        verify(convertPdfUseCase).execute(any())
    }
}
