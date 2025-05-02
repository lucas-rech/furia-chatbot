import { MessageType } from "./Chat";

type Props = {
  message: MessageType;
};

export default function Message({ message }: Props) {
  const isUser = message.sender === "user";

  return (
    <div className={`flex ${isUser ? "justify-end" : "justify-start"}`}>
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
