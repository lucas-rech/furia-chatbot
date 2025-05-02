export interface ChatRequest {
    input: string;
}

export interface ErrorResponse {
    error: string;
}

export interface ChatResponse {
    response: string;
}

const API_URL = import.meta.env.VITE_API_URL;

export async function sendMessage(data: ChatRequest): Promise<ChatResponse> {
    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data),
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