package com.lucasrech.furiaapi;

import com.lucasrech.furiaapi.services.BotService;
import com.lucasrech.furiaapi.services.GPAPIService;
import com.lucasrech.furiaapi.util.QuestionMatcher;
import com.lucasrech.furiaapi.util.ReadFiles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.TestConfiguration;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

@TestConfiguration
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
        readFilesMocked = mockStatic(ReadFiles.class);
        questionMatcherMocked = mockStatic(QuestionMatcher.class);
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

}
