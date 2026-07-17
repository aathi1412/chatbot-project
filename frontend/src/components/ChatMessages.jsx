import {  useRef, useEffect } from 'react';
import { ChatMessage } from './ChatMessage';
import './ChatMessages.css'; 


export function ChatMessages({ chatMessages }){ 
    const chatMessageRef = useAutoScroll(chatMessages);

    return (
        <div className="chat-messages-container"
        ref = {chatMessageRef}>
            { chatMessages.map((chatMessage) => {
                return (
                    <ChatMessage 
                        message= {chatMessage.message}
                        sender= {chatMessage.sender}
                        key={chatMessage.id}
                        time={chatMessage.time}
                        type={chatMessage.type}
                    />
                );
            })}
        </div>
    );
}

function useAutoScroll(dependencies){
    const chatMessageRef = useRef(null);

    useEffect(() => {
        const containerElem = chatMessageRef.current;
        if(containerElem){
            containerElem.scrollTop = containerElem.scrollHeight;
        }
    }, [dependencies]);

    return chatMessageRef;
}