package hibernate.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hibernate.entity.Run;
import hibernate.utils.DaoProvider;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.io.IOException;

public class RunEndpoint extends ServerResource{
    @Get
    public String methodGet(){
        //List<hibernate.entity.Run> runs = DaoProvider.getInstance().getRunDao().getAll();
        ObjectMapper mapper = new ObjectMapper();

        try {
            String runIdText = getAttribute("runId");
            Integer runId = Integer.parseInt(runIdText);
            Run run = DaoProvider.getInstance().getRunDao().getRun(runId);
            String json = mapper.writeValueAsString(run);
            return json;
        }catch (JsonProcessingException e) {
            e.printStackTrace();
            e.printStackTrace();

            getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);
            return e.getMessage();
        }


    }
    @Delete
    public void methodDelete(){
        ObjectMapper mapper = new ObjectMapper();

        String runIdText = getQueryValue("runId");

        if (runIdText!=null && runIdText.length()>0) {
            Integer runId = Integer.parseInt(runIdText);
            DaoProvider.getInstance().getRunDao().delete(runId);


        }else {
            getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
        }

    }

    @Post
    public String methodPost(Representation entity){
        try {
            String body = entity.getText();
            ObjectMapper mapper = new ObjectMapper();
            Run run = mapper.readValue(body,Run.class);

            DaoProvider.getInstance().getRunDao().save(run);

            return run.getId().toString();

        } catch (IOException e) {
            e.printStackTrace();

            getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);

            return e.getMessage();
        }
    }


}
