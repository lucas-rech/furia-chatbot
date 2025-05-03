import { useEffect, useState } from "react";
import { getCommands } from "../services/shortcutService";

type Props = {
  onSend: (text: string) => void;
};

export default function MessageInput({ onSend }: Props) {
  const [text, setText] = useState("");
  const [showCommands, setShowCommands] = useState(false);
  const [selectedCommandIndex, setSelectedCommandIndex] = useState(-1);
  const [commands, setCommands] = useState<Map<string, string>>(new Map());

  useEffect(() => {
    const fetchCommands = async () => {
      try {
        const commandsData = await getCommands();
        setCommands(commandsData);
      } catch (error) {
        console.error("Error fetching commands:", error);
      }
    };
    fetchCommands();
  }, []);

  const handleSend = () => {
    if (text.trim() === "") return;
    onSend(text);
    setText("");
    setShowCommands(false);
    setSelectedCommandIndex(-1);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setText(value);

    if (value.startsWith("/")) {
      setShowCommands(true);
    } else {
      setShowCommands(false);
      setSelectedCommandIndex(-1);
    }
  };

  // Função responsável por retornar lógica de clique de comando
  const handleCommandClick = (shortcut: string) => {
    const description = commands.get(shortcut);
    if (description) {
      onSend(description); // Envia a descrição do atalho como resposta
    }
    setText("");
    setShowCommands(false);
    setSelectedCommandIndex(-1);
  };

  // Função responsável pelo scroll dos atalhos
  const scrollToCommand = (index: number) => {
    const commandElement = document.getElementById(`command-${index}`);
    if (commandElement) {
      commandElement.scrollIntoView({ block: "nearest", behavior: "smooth" });
    }
  };

  // Função de acessibilidade para comandos de teclado no chat
  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    const commandKeys = Array.from(commands.keys());

    if (e.key === "Enter") {
      if (showCommands && selectedCommandIndex >= 0) {
        handleCommandClick(commandKeys[selectedCommandIndex]);
      } else {
        handleSend();
      }
    } else if (e.key === "ArrowDown") {
      setSelectedCommandIndex((prevIndex) => {
        const nextIndex = prevIndex < commandKeys.length - 1 ? prevIndex + 1 : 0;
        scrollToCommand(nextIndex);
        return nextIndex;
      });
    } else if (e.key === "ArrowUp") {
      setSelectedCommandIndex((prevIndex) => {
        const nextIndex = prevIndex > 0 ? prevIndex - 1 : commandKeys.length - 1;
        scrollToCommand(nextIndex);
        return nextIndex;
      });
    } else if (e.key === "Escape") {
      setShowCommands(false);
      setSelectedCommandIndex(-1);
    }
  };

  return (
    <div className="p-4 flex flex-col rounded-b-2xl relative">
      {showCommands && (
        <div className="absolute bottom-full mb-2 bg-[#0606063b] rounded-lg shadow-lg p-2 w-[90%] max-h-40 overflow-y-auto">
          {Array.from(commands.entries()).map(([shortcut, description], index) => (
            <div
              key={`${shortcut}-${index}`}
              id={`command-${index}`}
              className={`cursor-pointer p-2 pl-3 rounded-lg ${
                index === selectedCommandIndex ? "bg-[#ff8000] text-white" : "hover:bg-[#ff9900ba]"
              }`}
              onClick={() => handleCommandClick(shortcut)}
            >
              <div className="font-bold text-white">{shortcut}</div>
              <div className="text-sm text-white">{description}</div> {/* Exibe a descrição */}
            </div>
          ))}
        </div>
      )}
      <div className="flex items-center">
        <input
          type="text"
          className="flex-1 border-[1px] bg-[#ffffffe8] border-black rounded-lg p-2 mr-2 focus:outline-none focus:ring focus:border-[2px]"
          placeholder="Digite sua mensagem..."
          value={text}
          onChange={handleInputChange}
          onKeyDown={handleKeyDown}
        />
        <button
          onClick={handleSend}
          className="bg-[#FF9900] w-[25%] text-white px-4 py-2 rounded-lg hover:bg-[#ff8000] hover:scale-105 transition"
        >
          Enviar
        </button>
      </div>
    </div>
  );
}