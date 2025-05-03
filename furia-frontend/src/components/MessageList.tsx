import { MessageType } from "./Chat";
import Message from "./Message";

type Props = {
  messages: MessageType[];
};

export default function MessageList({ messages }: Props) {

  const presentation: MessageType = {
    id: 1,
    text: "Olá, eu me chamo Panto! Sou o mascote da FURIA e-Sports!\n\nComo posso ajudar?",
    sender: "bot",
  }

  return (
    <div className="flex-1 overflow-y-auto p-4 space-y-2">
      <Message key={presentation.id} message={presentation}></Message>
      {messages.map((msg) => (
        <Message key={msg.id} message={msg} />
      ))}
    </div>
  );
}
