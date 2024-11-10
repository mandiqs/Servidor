package br.pucpr.authserver.author

import br.pucpr.authserver.errors.NotFoundException
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.repository.findByIdOrNull

class AuthorServiceTest {
    private companion object {
        const val TEST_AUTHOR_ID = 1L
        const val TEST_NAME = "Test Author"
    }

    private lateinit var authorService: AuthorService
    private val authorRepository = mockk<AuthorRepository>()

    @BeforeEach
    fun setup() {
        authorService = AuthorService(authorRepository)
    }

    @AfterEach
    fun cleanup() {
        clearAllMocks()
    }

    @Test
    fun `createAuthor should save and return author`() {
        val author = Author(name = TEST_NAME)
        val savedAuthor = author.copy(id = TEST_AUTHOR_ID)

        every { authorRepository.save(author) } returns savedAuthor

        val result = authorService.createAuthor(author)

        result shouldBe savedAuthor
        verify { authorRepository.save(author) }
    }

    @Test
    fun `getAuthor should return author when found`() {
        val author = Author(id = TEST_AUTHOR_ID, name = TEST_NAME)
        every { authorRepository.findByIdOrNull(TEST_AUTHOR_ID) } returns author

        val result = authorService.getAuthor(TEST_AUTHOR_ID)

        result shouldBe author
        verify { authorRepository.findByIdOrNull(TEST_AUTHOR_ID) }
    }

    @Test
    fun `getAuthor should throw NotFoundException when author not found`() {
        every { authorRepository.findByIdOrNull(TEST_AUTHOR_ID) } returns null

        assertThrows<NotFoundException> {
            authorService.getAuthor(TEST_AUTHOR_ID)
        }.message shouldBe "No author found with ID $TEST_AUTHOR_ID"
    }
}