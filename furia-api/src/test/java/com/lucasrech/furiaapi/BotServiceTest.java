package com.lucasrech.furiaapi;

import com.lucasrech.furiaapi.exceptions.TimeOutProcessingQuestion;
import com.lucasrech.furiaapi.services.BotService;
import com.lucasrech.furiaapi.services.GPAPIService;
import com.lucasrech.furiaapi.util.QuestionMatcher;
import com.lucasrech.furiaapi.util.ReadFiles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BotServiceTest {

    @InjectMocks
    private BotService botService;

    @Mock
    private GPAPIService gpapiService;

    @Mock
    private MockedStatic<ReadFiles> readFilesMocked;

    @Mock
    private MockedStatic<QuestionMatcher> questionMatcherMocked;

    @BeforeEach
    void setUp() {
        gpapiService = mock(GPAPIService.class);
        botService = new BotService(gpapiService);
    }

    @Test
    void testTalkBotWithMatch() {
        HashMap<String, String> quotes = new HashMap<>();
        quotes.put("qual é a cor?", "A cor do PANTO é preto.");

        readFilesMocked.when(() -> ReadFiles.readQuotesFile(anyString(), any()))
                .thenAnswer(invocation -> {
                    HashMap<String, String> map = invocation.getArgument(1);
                    map.putAll(quotes);
            return null;
        });

        questionMatcherMocked.when(() ->
                QuestionMatcher.findBestMatch((any()), eq("qual é a cor?")))
                .thenReturn("A cor do PANTO é preto.");

        String response = botService.talkBot("qual é a cor?");
        assertEquals("A cor do PANTO é preto.", response);
    }

    @Test
    void testTalkBotWithoutMatchFallbackToGPAPI() {

        readFilesMocked.when(() -> ReadFiles.readQuotesFile(anyString(), any())).thenAnswer(_ -> null);
        questionMatcherMocked.when(() -> QuestionMatcher.findBestMatch(any(), eq("Qual é a a fórmula para a entropia de Shannon?"))).thenReturn(null);

        when(gpapiService.chatAPI("Qual é a a fórmula para a entropia de Shannon?")).thenReturn("Resposta da API");

        String response = botService.talkBot("Qual é a a fórmula para a entropia de Shannon?");
        assertEquals("Resposta da API", response);
    }

    @Test
    void testTalkBotGPAPIException() {
        // Mock do ReadFiles
        readFilesMocked.when(() -> ReadFiles.readQuotesFile(anyString(), any())).thenAnswer(_ -> null);

        // Mock do QuestionMatcher
        questionMatcherMocked.when(() -> QuestionMatcher.findBestMatch(any(), eq("qual é a cor?"))).thenReturn(null);

        // Mock do GPAPIService com exceção
        when(gpapiService.chatAPI("qual é a cor?")).thenThrow(new TimeOutProcessingQuestion());

        assertThatThrownBy(() -> botService.talkBot("qual é a cor?"))
                .isInstanceOf(TimeOutProcessingQuestion.class)
                .hasMessageContaining("Time over processing question");
    }


}
