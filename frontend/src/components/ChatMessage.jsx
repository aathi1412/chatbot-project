import RobotProfileImage from '../assets/robot-profile.png';
import userProfileImage from '../assets/profile-1.jpg';
import dayjs from 'dayjs';
import './ChatMessage.css';


export function ChatMessage({message, sender, time}){
    
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
                    {message}

                    {time && (
                        <div className='chat-message-time'>
                            {dayjs(time).format('HH: mma')}
                        </div>
                    )}
                </div>

                <div className="chat-message-image">
                    {message}

                    {time && (
                        <div className='chat-message-time'>
                            {dayjs(time).format('HH: mma')}
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