import { MessageType } from "./Chat";

import pantoImg  from "../assets/panto.png";

type Props = {
  message: MessageType;
};

export default function Message({ message }: Props) {
  const isUser = message.sender === "user";
  const botImage = pantoImg;

  return (
    <div className={`flex ${isUser ? "justify-end" : "justify-start"}`}>

      {/* Conditional rendering of the bot image */}
      {!isUser && (
        <img
          src={botImage}
          alt="Bot"
          className="w-8 h-8 rounded-full mr-2"
        />
      )}

      {/* Condição para diferenciar mensagem do bot e do usuário */}
      <div
        className={`px-4 py-2 rounded-xl max-w-xs ${
          isUser ? "bg-[#FF9900] text-white" : "bg-[#272727] text-white"
        }`}
      >
        {message.text}
      </div>
    </div>
  );
}
