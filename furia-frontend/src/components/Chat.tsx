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
          text: "Erro ao conectar com o servidor.",
          sender: "bot",
        },
      ]);
    }
  };

  return (
    <div className="flex flex-col h-full w-[380px] mx-auto rounded-2xl bg-[#3b3b3bd6]">
      <MessageList messages={messages} />
      <MessageInput onSend={handleSendMessage} />
    </div>
  );
}
