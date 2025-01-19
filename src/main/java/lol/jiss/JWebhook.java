package lol.jiss;

import com.hakan.jdw.Webhook;

import java.awt.Color;

/**
 * Utility class to send Webhooks to Discord.
 * Version: 1.0.0
 */
public final class JWebhook {

    /**
     * Sends a Webhook to the specified URL, with the ability to customize
     * the basic content and the embedded part.
     *
     * @param url         Discord Webhook URL
     * @param content     Main content to be sent to the channel
     * @param title       Embed title (can be null if not desired)
     * @param description Embed description (can be null if not desired)
     * @param color       Embed color (can be null if not desired)
     * @param embedUrl    Optional URL to link in the title (can be null)
     * @param timeZone    Time zone for the timestamp (e.g., "UTC+3"), can be null
     */
    public static void sendWebhook(String url,
                                   String content,
                                   String title,
                                   String description,
                                   Color color,
                                   String embedUrl,
                                   String timeZone) {

        Webhook.Builder builder = new Webhook.Builder()
                .content(content);

        if (title != null || description != null || color != null || embedUrl != null) {
            builder.embed(embedBuilder -> {
                if (title != null) {
                    embedBuilder.title(title);
                }
                if (description != null) {
                    embedBuilder.description(description);
                }
                if (color != null) {
                    embedBuilder.color(color);
                }
                if (embedUrl != null) {
                    embedBuilder.url(embedUrl);
                }
                if (timeZone != null) {
                    embedBuilder.timestamp(System.currentTimeMillis(), timeZone);
                }
                return embedBuilder;
            });
        }

        Webhook webhook = builder.build();
        webhook.execute(url);
    }

    /**
     * Debug method to test the Webhook.
     * @param url Insert the Webhook URL as the first argument to test the Webhook.
     */
    public static void main(String[] url) {
        String testUrl = url[0];

        if (testUrl == null) {
            System.out.println("Please provide a Webhook URL as the first argument.");
            return;
        }

        sendWebhook(
                testUrl,
                "Message content",
                "Example Title",
                "Embed description",
                new Color(108, 0, 184),
                "https://github.com/litjisz",
                "UTC+3"
        );
    }
}