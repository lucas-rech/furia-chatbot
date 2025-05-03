import Chat from "./components/Chat"

function App() {

  return (
    <div className="flex flex-col md:flex-row h-full md:h-[90vh] w-[80vw] justify-center items-center md:items-start gap-12 pt-15">
      <div className="flex flex-col text-white w-full md:w-[30%]">
        <h1 className="text-2xl md:text-2xl font-bold text-[#FF9900] break-words">Panto, o mascote oficial da FURIA!</h1>
        <p className="mt-6 text-[1rem]">
          Panto é uma pantera estilosa, carismática e com reflexos mais rápidos que um headshot de AWP. 
          Ele é o mascote oficial da FURIA Esports e representa toda a garra, atitude e ousadia do time. 
          <br /><br />
          Com sua pelagem preta e olhar afiado, Panto não só inspira os jogadores como também diverte os fãs 
          com seu bom humor felino e comentários afiados. Sempre pronto pra arranhar a concorrência, 
          ele é a alma da torcida furiosa!
        </p>
      </div>
      <div className="rounded-lg w-fit">
        <Chat />
      </div>
    </div>
  )
}

export default App
