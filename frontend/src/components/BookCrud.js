import React, { useState, useEffect } from 'react';
import { getBooks, createBook, updateBook, deleteBook } from '../services/bookService';

const BookCrud = () => {
    const [books, setBooks] = useState([]); // Lista de livros
    const [bookForm, setBookForm] = useState({ title: '', author: '' }); // Dados do formulário
    const [editing, setEditing] = useState(false); // Estado de edição
    const [currentBookId, setCurrentBookId] = useState(null); // ID do livro que está sendo editado
    const [responseMessage, setResponseMessage] = useState(null); // Estado para armazenar a resposta da API

    useEffect(() => {
        fetchBooks(); // Carrega a lista de livros ao carregar o componente
    }, []);

    // Função para buscar todos os livros
    const fetchBooks = async () => {
        try {
            const response = await getBooks();
            setBooks(response.data); // Atualiza o estado com a lista de livros
        } catch (error) {
            console.error('Erro ao buscar livros:', error);
        }
    };

    // Função para lidar com mudanças no formulário
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setBookForm({ ...bookForm, [name]: value });
    };

    // Função para criar ou atualizar um livro
    const handleCreateOrUpdate = async (e) => {
        e.preventDefault();
        try {
            let response;
            if (editing) {
                response = await updateBook(currentBookId, bookForm); // Atualiza o livro se estiver em modo de edição
            } else {
                response = await createBook(bookForm); // Cria um novo livro se não estiver em modo de edição
            }
            setResponseMessage(`Sucesso: ${response.status} - ${response.statusText}`); // Exibe mensagem de sucesso
            setBookForm({ title: '', author: '' }); // Limpa o formulário
            setEditing(false);
            setCurrentBookId(null);
            fetchBooks(); // Atualiza a lista de livros
        } catch (error) {
            if (error.response) {
                // Se a resposta da API contém um erro (ex.: autenticação)
                setResponseMessage(`Erro: ${error.response.status} - ${error.response.statusText}`);
            } else {
                // Se o erro é devido a problemas de rede ou outro problema inesperado
                setResponseMessage("Erro: não foi possível conectar ao servidor.");
            }
        }
    };

    // Função para editar um livro
    const handleEdit = (book) => {
        setEditing(true); // Ativa o modo de edição
        setCurrentBookId(book.id);
        setBookForm({ title: book.title, author: book.author });
    };

    // Função para excluir um livro
    const handleDelete = async (id) => {
        try {
            await deleteBook(id);
            fetchBooks(); // Atualiza a lista de livros após a exclusão
        } catch (error) {
            console.error('Erro ao excluir o livro:', error);
        }
    };

    return (
        <div>
            <h3>Cadastrar Livro:</h3>
            <form onSubmit={handleCreateOrUpdate}>
                <input
                    type="text"
                    name="title"
                    value={bookForm.title}
                    onChange={handleInputChange}
                    placeholder="Título"
                    required
                />
                <input
                    type="text"
                    name="author"
                    value={bookForm.author}
                    onChange={handleInputChange}
                    placeholder="Autor"
                    required
                />
                <button type="submit">{editing ? 'Atualizar' : 'Adicionar'}</button>
            </form>

            {/* Exibe a resposta da API aqui */}
            {responseMessage && <p>{responseMessage}</p>}

            <h3>Buscar Livro:</h3>
            <ul>
                {books.map((book) => (
                    <li key={book.id}>
                        <strong>{book.title}</strong> - {book.author}
                        <button onClick={() => handleEdit(book)}>Editar</button>
                        <button onClick={() => handleDelete(book.id)}>Excluir</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default BookCrud;



