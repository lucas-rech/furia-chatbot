import { useState } from "react";
import MessageList from "./MessageList";
import MessageInput from "./MessageInput";
import { ChatResponse, sendMessage } from "../services/apiService.ts";

type Sender = "user" | "bot";

export interface MessageType {
  id: number;
  text: string;
  sender: Sender;
}


export default function Chat() {
  const [messages, setMessages] = useState<MessageType[]>([]);
  const handleSendMessage = async (text: string) => {
    const userMessage: MessageType = {
      id: Date.now(),
      text,
      sender: "user",
    };

    setMessages((prev) => [...prev, userMessage]);

    try {
      const data: ChatResponse = await sendMessage({ input: text });
      const botMessage: MessageType = {
        id: Date.now() + 1,
        text: data.response,
        sender: "bot",
      };

      setMessages((prev) => [...prev, botMessage]);
    } catch (err: any) {
      setMessages((prev) => [
        ...prev,
        {
          id: Date.now() + 1,
          text: "Desculpe, não consigo ajudar no momento!",
          sender: "bot",
        },
      ]);
    }
  };

  return (
    <div className="flex flex-col min-h-[550px] h-[580px] md:h-[780px] w-[360px] md:w-[400px] mx-auto rounded-2xl bg-[var(--color-chat)]">
      <MessageList messages={messages} />
      <MessageInput onSend={handleSendMessage} />
    </div>
  );
}
