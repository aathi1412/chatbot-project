import RobotProfileImage from '../assets/robot-profile.png';
import userProfileImage from '../assets/user-profile.png';
import dayjs from 'dayjs';
import './ChatMessage.css';


export function ChatMessage({message, sender, time, type}){

        return (
            <div 
                className={sender==='user'
                    ? 'chat-message-user'
                    : 'chat-message-robot'}
            >
                {sender === 'robot' && (
                    <img
                        src={RobotProfileImage}
                        width="45"
                        className="chat-message-profile"
                        alt="robot profile image"
                    />
                )}

                <div className="chat-message-text">
                    {type === "TEXT" && message}
                    {type === "ERROR" && message}

                    {type === "IMAGE" && (
                        <img src={message} alt="Generated AI" className="chat-message-image" />
                    )}

                    {type === "LOADING" && (
                        <img src={message} className="loading-gif" alt="Loading" />
                    )}

                    {time && (
                        <div className="chat-message-time">
                            {dayjs(time).format("HH:mm A")}
                        </div>
                    )}
                </div>

                {sender === 'user' && (
                    <img
                        src={userProfileImage}
                        width="45"
                        className="chat-message-profile"
                        alt="user profile image"
                    />

                )}
            </div>
        );

    }