package januszauto

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import org.grails.web.json.JSONElement

@Transactional
class OtherSystemService {
//https://guides.grails.org/grails-micronaut-http/guide/index.html


    public static final GENDER_API_URL = "https://api.genderize.io"
    public static final AGE_API_URL = "https://api.agify.io"
    HttpClient genderClient = HttpClient.create(GENDER_API_URL.toURL())
    HttpClient ageClient = HttpClient.create(AGE_API_URL.toURL())

    def serviceMethod() {
        List<Mechanic> ageNotKnown = Mechanic.findAllByAgeIsNull();
        List<Mechanic> genderNotKnown = Mechanic.findAllByGenderIsNull();


        ageNotKnown.each {
            HttpRequest request = HttpRequest.GET("/?name=" + it.firstName)
            HttpResponse<String> resp = ageClient.toBlocking().exchange(request, String)
            it.age = JSON.parse(resp.body()).getAt("age")
            it.save(flush: true, failOnError: true)
        }

        genderNotKnown.each {
            HttpRequest request = HttpRequest.GET("/?name=" + it.firstName)
            HttpResponse<String> resp = genderClient.toBlocking().exchange(request, String)
            it.gender = JSON.parse(resp.body()).getAt("gender")
            it.save(flush:true, failOnError: true)
        }

        return genderNotKnown
    }
}
