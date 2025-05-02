import { useState } from "react";

type Props = {
  onSend: (text: string) => void;
};

export default function MessageInput({ onSend }: Props) {
  const [text, setText] = useState("");

  const handleSend = () => {
    if (text.trim() === "") return;
    onSend(text);
    setText("");
  };

  return (
    <div className="p-4 bg-white border-t flex">
      <input
        type="text"
        className="flex-1 border rounded-lg p-2 mr-2 focus:outline-none focus:ring"
        placeholder="Digite sua mensagem..."
        value={text}
        onChange={(e) => setText(e.target.value)}
        onKeyDown={(e) => e.key === "Enter" && handleSend()}
      />
      <button
        onClick={handleSend}
        className="bg-[#FF9900] text-white px-4 py-2 rounded-lg hover:bg-[#ff8000] transition"
      >
        Enviar
      </button>
    </div>
  );
}
