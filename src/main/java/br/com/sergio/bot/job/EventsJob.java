package br.com.sergio.bot.job;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sergio.bot.util.dao.TableEvents;

@Service
public class EventsJob implements Job {

	@Autowired
	private BotSender botSender;

	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH) + 1;
		Map<Integer, String> map = TableEvents.getMapevents().get(month);
		if (map != null) {
			int day = cal.get(Calendar.DAY_OF_MONTH);
			String message = map.get(day);
			if (message != null) {
//				sendMessage(message);
			}
		}
	}

	private void sendMessage(String message) {
		botSender.send(392109665L, message); // My window
		botSender.send(-310539280L, message); // TESTE Group
		botSender.send(-308892016L, message); // TESTANDO Group
		botSender.send(-253175991L, message); // TESTANDO Group Guilherme
	}

}
