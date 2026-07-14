import { useState, useEffect } from 'react';
import { ChatInput } from './components/ChatInput';
import { ChatMessages } from './components/ChatMessages';
import { Chatbot } from 'supersimpledev';
import './App.css' 

   
function App(){
    const [chatMessages, setChatMessages] = useState(JSON.parse(localStorage.getItem('messages')) || []);

    useEffect(() => {
        Chatbot.addResponses({
            'hi': 'hii, welcome back',
            'goodbye': 'Goodbye. Have a great day!',
            'give me a unique id': function(){
                return `Sure! Here's a unique ID: ${crypto.randomUUID()} `;
            }
        });
    }, []);

    useEffect(() => {
        localStorage.setItem('messages', JSON.stringify(chatMessages));
    }, [chatMessages]);

    return (
        <div className="app-container">
            
            <ChatMessages 
                chatMessages = {chatMessages}
            />

            <p className="default-welcome-message"> {chatMessages.length === 0 && 'Welcome to the chatbot! Send a message using the textbox below.'} </p>

            <ChatInput 
                chatMessages = {chatMessages}
                setChatMessages = {setChatMessages}  
            />
            
        </div>
    );
        
    }

export default App
