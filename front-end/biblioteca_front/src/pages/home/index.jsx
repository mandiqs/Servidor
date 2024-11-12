import React, { useState } from 'react';
import { useEffect } from 'react';
import './style.css'
import api from '../../services/api'

function Home() {
const [users, setUsers] = useState([])

  async function getUsers() {
    try {
        const usersFromApi = await api.get('/books');
        setUsers(usersFromApi.data);
    } catch (error) {
        console.error("Erro ao buscar usuários:", error);
    }
}

  useEffect(() => {
    getUsers()
  }, [])


  return (
    <div>
      <h1>Olá react</h1>
      {users.map((user, index) => (
        <p key={index}>{user.title}</p> // Assumindo que cada usuário tem uma propriedade 'name'
      ))}
    </div>
    
  )
}

export default Home
