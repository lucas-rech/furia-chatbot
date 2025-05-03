import { getShortcuts } from "./apiService";

// Retorna um map em ordem alfabética de todos os comandos recebidos via endpoint
export async function getCommands(): Promise<Map<string, string>> {
    try {
        const shortcuts = await getShortcuts(); 
        const commands = new Map<string, string>();

        // Adiciona o atalho e a descrição ao Map
        for (const shortcut of shortcuts) {
            if(shortcut.description.trim()[0] !== "/") {
                continue;
            }
            commands.set(shortcut.description, shortcut.shortcut)
        }
        
        const sortedMap = new Map([...commands.entries()].sort());
        return sortedMap;
    } catch (error) {
        console.error("Error fetching commands:", error);
        throw new Error("Failed to fetch commands");
    }
}