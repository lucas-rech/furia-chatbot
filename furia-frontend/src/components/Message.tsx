import { MessageType } from "./Chat";

import pantoImg  from "../assets/panto.png";
import terroristImg from "../assets/terrorist.png";

type Props = {
  message: MessageType;
};


type SenderImg = {
  img: string,
  alt: string
}

const SenderImage = ({ img, alt, className }: SenderImg & { className: string}) => (
  <img src={img} alt={alt} className={`w-8 h-8 rounded-full ${className}`} />
);

export default function Message({ message }: Props) {
  const isUser = message.sender === "user";

  const botImage: SenderImg = {
    img: pantoImg,
    alt: "imagem de avatar do panto"
  };

  const usrImage: SenderImg = {
    img: terroristImg,
    alt: "imagem de avatar do usuário"
  }

  const sender = isUser ? usrImage : botImage;

  const messageStyles = isUser
    ? "bg-[#FF9900] text-white"
    : "bg-[#FFFFFF] text-black";

  return (
    <div className={`flex ${isUser ? "justify-end" : "justify-start"}`}>
      {!isUser && <SenderImage img={sender.img} alt={sender.alt} className="mr-2" />}
      <div className={`px-4 py-2 rounded-xl max-w-xs ${messageStyles}`}>
        {message.text}
      </div>
      {isUser && <SenderImage img={sender.img} alt={sender.alt} className="ml-2" />}
    </div>
  );
}
