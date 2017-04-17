package za.co.pas.tool.multidownloader;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.pas.lib.gemeenskaplik.AlgemeneGemeenskaplik;
import za.co.pas.lib.gemeenskaplik.ArgsReël;
import za.co.pas.lib.multidownloader.MultiDownloader;
import za.co.pas.lib.multidownloader.logging.LogListener;
import za.co.pas.tool.multidownloader.logging.MyLogFormatter;

/**
 *
 * @author Andre Labuschagne
 */
public class Downloader implements LogListener{

    private static final Logger LOG = Logger.getLogger(Downloader.class.getName());
    private static final ConsoleHandler LOG_HANDLER = new ConsoleHandler();

    static {
        Handler[] handlers = LOG.getHandlers();
        for (Handler handler : handlers) {
            LOG.removeHandler(handler);
        }
        try {
            LOG_HANDLER.setEncoding("UTF-8");
        } catch (SecurityException | UnsupportedEncodingException e) {
            LOG.log(Level.SEVERE, "Error while setting up logging encoding", e);
        }
        LOG_HANDLER.setFormatter(new MyLogFormatter());
        LOG.addHandler(LOG_HANDLER);
        LOG.setUseParentHandlers(false);
    }

    private static final String PARTS = "--parts";
    private static final String URL = "--url";
    private static final String BUFFER_SIZE = "--buffersize";

    public static void main(String args[]) {
        try {
            int numberOfParts = 4;
            int bufferSize = 8 * 1024; //8Kb
            List<ArgsReël> argsReëls = new LinkedList<>();
            argsReëls.add(new ArgsReël(URL, true));
            argsReëls.add(new ArgsReël(PARTS, true));
            argsReëls.add(new ArgsReël(BUFFER_SIZE, true));
            argsReëls.add(new ArgsReël(AlgemeneGemeenskaplik.OPSIE_HELP, false));
            Map<String, String> mapArgs = AlgemeneGemeenskaplik.WisselArgs(argsReëls, args);
            String aUrl;
            if (mapArgs.containsKey(AlgemeneGemeenskaplik.OPSIE_HELP)) {
                PrintUsage();
                return;
            }
            if (!mapArgs.containsKey(URL)) {
                LOG.log(Level.WARNING, "Usage!");
                PrintUsage();
                return;
            } else {
                aUrl = mapArgs.get(URL);
            }
            if (mapArgs.containsKey(PARTS)) {
                numberOfParts = Integer.parseInt(mapArgs.get(PARTS));
            }
            URL url = new URL(aUrl);
            MultiDownloader downloaderApp = new MultiDownloader(url, numberOfParts, bufferSize);
            downloaderApp.setLogListener(new Downloader());
            downloaderApp.go();
        } catch (MalformedURLException e) {
            LOG.log(Level.SEVERE, "Error checking file: {0}", e.toString());
        }
    }

    private static void PrintUsage() {
        StringBuilder sb = new StringBuilder();
        String[] lines = new String[12];
        lines[0] = " Usage:";
        lines[1] = "        java -jar MultiDownloader.jar <--url=url>";
        lines[2] = "                                      [--parts=numberOfThreads] [--help]";
        lines[3] = "                                      [--buffersize=size]";
        lines[4] = "  ";
        lines[5] = " Options:";
        lines[6] = "          --url=url               : The url of the file to download";
        lines[7] = "          --parts=numberOfThreads : Optional, default is 4. Number of";
        lines[8] = "                                    threads to run";
        lines[9] = "          --buffersize=size       : Optional, default is 8192. Reading";
        lines[10]= "                                    buffer size when concatenating the";
        lines[11]= "                                    file parts";
        AlgemeneGemeenskaplik.TekenRaam(sb, lines);
        LOG.log(Level.INFO, sb.toString());
    }

    @Override
    public void logInfo(String info, String... more) {
        LOG.log(Level.INFO, info, more);
    }

    @Override
    public void logWarn(String info, String... more) {
        LOG.log(Level.WARNING, info, more);
    }

    @Override
    public void logError(String info, String... more) {
        LOG.log(Level.SEVERE, info, more);
    }
}
