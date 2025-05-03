export interface ChatRequest {
    input: string;
}

export interface ErrorResponse {
    error: string;
}

export interface ChatResponse {
    response: string;
}

export interface Shortcut {
    shortcut: string,
    description: string,
}


const API_URL = import.meta.env.VITE_API_URL;

export async function sendMessage(data: ChatRequest): Promise<ChatResponse> {
    try {
        const response = await fetch(`${API_URL}/talk`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            const errorData: ErrorResponse = await response.json();
            throw new Error(errorData.error || "Unknown error");
        }

        const result = await response.json();
        return result as ChatResponse;
    } catch (error) {
        console.error("Error sending message:", error);
        throw new Error("Failed to send message");
    }    
}

// Função que consome o endpoint da api externa
export async function getShortcuts(): Promise<Shortcut[]> {
    try {
        const response = await fetch(`${API_URL}/shortcuts`, {
            method: "GET",
            headers: { "Content-Type": "application/json" },
        });

        if (!response.ok) {
            const errorData: ErrorResponse = await response.json();
            throw new Error(errorData.error || "Unknown error");
        }

        const result = await response.json();

        console.log(result)
        // Verifica se o resultado é um array e contém os campos esperados
        if (Array.isArray(result)) {
            const shortcuts: Shortcut[] = result.map((item) => ({
                shortcut: item.shortcut,
                description: item.description,
            }));
            return shortcuts;
        } else {
            throw new Error("Invalid response format");
        }
    } catch (error) {
        console.error("Error fetching shortcuts:", error);
        throw new Error("Failed to fetch shortcuts");
    }
}

