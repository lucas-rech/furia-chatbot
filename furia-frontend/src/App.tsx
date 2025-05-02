import Chat from "./components/Chat"

function App() {

  return (
    <div className="flex flex-col h-[90vh] items-center justify-center">
      <div className="shadow-amber-100 rounded-lg h-full w-fit">
        <Chat />
      </div>
    </div>
  )
}

export default App
