import React, { useState, useEffect } from 'react';
import './style.css';
import api from '../../services/api';

function Home() {
  const [books, setBooks] = useState([]);
  const [newBook, setNewBook] = useState({ title: '', publicationDate: '' });
  const [editingBookId, setEditingBookId] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredBooks, setFilteredBooks] = useState([]);

  async function getBooks() {
    try {
      const booksFromApi = await api.get('/books');
      setBooks(booksFromApi.data);
      setFilteredBooks(booksFromApi.data);
    } catch (error) {
      console.error("Erro ao buscar livros:", error);
    }
  }

  async function addBook() {
    try {
      const response = await api.post('/books', newBook);
      setBooks([...books, response.data]);
      setFilteredBooks([...filteredBooks, response.data]);
      setNewBook({ title: '', publicationDate: '' });
    } catch (error) {
      console.error("Erro ao cadastrar livro:", error);
    }
  }

  function startEditingBook(book) {
    setEditingBookId(book.id);
    setNewBook({ title: book.title, publicationDate: book.publicationDate });
  }

  async function updateBook() {
    try {
      const response = await api.put(`/books/${editingBookId}`, newBook);
      const updatedBooks = books.map((book) =>
        book.id === editingBookId ? response.data : book
      );
      setBooks(updatedBooks);
      setFilteredBooks(updatedBooks);
      setEditingBookId(null);
      setNewBook({ title: '', publicationDate: '' });
    } catch (error) {
      console.error("Erro ao editar livro:", error);
    }
  }

  async function deleteBook(id) {
    const confirmDelete = window.confirm("Tem certeza que deseja excluir este livro?");
    if (confirmDelete) {
      try {
        await api.delete(`/books/${id}`); // Realiza a exclusão no servidor
        const updatedBooks = books.filter((book) => book.id !== id); // Remove o livro da lista no frontend
        setBooks(updatedBooks);
        setFilteredBooks(updatedBooks);
        console.log(`Livro com ID ${id} excluído com sucesso.`);
      } catch (error) {
        console.error("Erro ao excluir livro:", error);
      }
    }
  }

  function handleSearch() {
    const filtered = books.filter((book) =>
      book.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
      book.id.toString() === searchTerm.trim()
    );
    setFilteredBooks(filtered);
  }

  useEffect(() => {
    getBooks();
  }, []);

  return (
    <div className="container">
      <h1>Gerenciamento de Livros</h1>

      <div className="card">
        <h2>Cadastrar Livro</h2>
        <input
          type="text"
          placeholder="Título do livro"
          value={newBook.title}
          onChange={(e) => setNewBook({ ...newBook, title: e.target.value })}
        />
        <input
          type="date"
          placeholder="Data de publicação"
          value={newBook.publicationDate}
          onChange={(e) => setNewBook({ ...newBook, publicationDate: e.target.value })}
        />
        <button onClick={addBook}>Cadastrar</button>
      </div>

      <div className="card">
        <h2>Pesquisar Livros</h2>
        <input
          type="text"
          placeholder="Digite o nome ou id"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <button onClick={handleSearch}>Pesquisar</button>
      </div>

      <div className="card">
        <h2>Lista de Livros</h2>
        {filteredBooks.map((book) => (
          <div className="book-item" key={book.id}>
            {editingBookId === book.id ? (
              <div>
                <h2>Editar Livro</h2>
                <input
                  type="text"
                  placeholder="Título do livro"
                  value={newBook.title}
                  onChange={(e) => setNewBook({ ...newBook, title: e.target.value })}
                />
                <input
                  type="date"
                  placeholder="Data de publicação"
                  value={newBook.publicationDate}
                  onChange={(e) => setNewBook({ ...newBook, publicationDate: e.target.value })}
                />
                <div className="edit-button-group">
                  <button onClick={updateBook}>Atualizar</button>
                  <button onClick={() => setEditingBookId(null)}>Cancelar</button>
                </div>
              </div>
            ) : (
              <div>
                <p><strong>ID:</strong> {book.id}</p>
                <p><strong>Título:</strong> {book.title}</p>
                <p><strong>Data de Publicação:</strong> {book.publicationDate}</p>
                <div className="button-group">
                  <button onClick={() => startEditingBook(book)}>Editar</button>
                  <button onClick={() => deleteBook(book.id)}>Excluir</button>
                </div>
              </div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}

export default Home;












