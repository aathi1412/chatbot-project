import { useState } from 'react';
import { Chatbot } from 'supersimpledev';
import LoadingGif from '../assets/gifs/Loading-gif.gif';
import dayjs from 'dayjs';
import './ChatInput.css';

export function ChatInput({ chatMessages, setChatMessages }){
        const [inputText, setInputText] = useState('');
        const [isLoading, setIsLoading] = useState(false);

        function saveInputText(event){
            setInputText(event.target.value);
        }

        async function sendMessage(){
            if(isLoading || inputText === ''){
                return;
            }
            setIsLoading(true);

            const newChatMessage = [
                ...chatMessages,
                {
                    message: inputText ,
                    sender: "user",
                    id: crypto.randomUUID(),
                    time: dayjs().valueOf()
                }
            ];
            
            setChatMessages(newChatMessage);  

            setInputText('');

            setChatMessages([
                ...newChatMessage,
                {
                    message: <img className="loading-gif" src={LoadingGif} /> ,
                    sender: "robot",
                    id: crypto.randomUUID()
                }
            ]);

            const response = await Chatbot.getResponseAsync(inputText);
            setChatMessages([
                ...newChatMessage,
                {
                    message: response ,
                    sender: "robot",
                    id: crypto.randomUUID(),
                    time: dayjs().valueOf()
                }
            ]);

            setIsLoading(false);
        }

        function clearMessage(){
            setChatMessages([]);
        }
        function keyEvent(event){
            if(event.key === 'Enter'){
                sendMessage();
            }
            else if(event.key === 'Escape'){
                setInputText('');
            }
        }

        return (
            <div className="chat-input-container">
                <input 
                    placeholder="send a message to chatbot" 
                    size="30"
                    onChange={saveInputText}
                    value={inputText}
                    onKeyDown={keyEvent}
                    className="input-send-message"
                />
                <button 
                    onClick={sendMessage}
                    className="send-button"
                >send</button>

                <button 
                    onClick={clearMessage}
                    className="clear-button"
                >Clear
                </button>
            </div>
        );
    }
