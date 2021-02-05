package package1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class WynagrodzenieDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Konstruktor z jdbcTemplate
     * @param jdbcTemplate instancja jdbcTemplate
     */
    public WynagrodzenieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wynagrodzenie> list(){
        String sql="SELECT * FROM WYNAGRODZENIA";
        List<Wynagrodzenie> listWynagrodzenie = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Wynagrodzenie.class));
        return listWynagrodzenie;
    }
    
    /* (C)reate */
    public void save(Wynagrodzenie wynagrodzenie){
    	SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
    	insertActor.withTableName("wynagrodzenia").usingColumns("data","kwota_pdst","kwota_dod","nr_pracownika");
    	
    	BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(wynagrodzenie);
    	insertActor.execute(param);
    }
    
    /* (R)ead */
    public Wynagrodzenie get(int nr_wynagrodzenia){
        Object[] args = {nr_wynagrodzenia};
        String sql = "SELECT * FROM WYNAGRODZENIA WHERE NR_WYNAGRODZENIA = "+args[0];
        Wynagrodzenie wynagrodzenie = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Wynagrodzenie.class));
        return wynagrodzenie;
    }
    /* (U)pdate */
    public void update(Wynagrodzenie wynagrodzenie){
    	String sql = "UPDATE WYNAGRODZENIA SET data=:data, kwota_pdst=:kwota_pdst, kwota_dod=:kwota_dod, nr_pracownika=:nr_pracownika WHERE nr_wynagrodzenia=:nr_wynagrodzenia";
    	BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(wynagrodzenie);
    	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
    	
    	template.update(sql, param);
    }
    /* (D)elete */
    public void delete(int id){
    	String sql = "DELETE FROM WYNAGRODZENIA WHERE NR_WYNAGRODZENIA = ?";
    	jdbcTemplate.update(sql,id);
    }
    
    /* (G)et wynagrodzenia pracownika */
    public List<Wynagrodzenie> getWy(int nr_pracownika){
        Object[] args = {nr_pracownika};
        String sql = "SELECT * FROM WYNAGRODZENIA WHERE NR_PRACOWNIKA = "+args[0];
        List<Wynagrodzenie> listWynagrodzenie = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Wynagrodzenie.class));
        return listWynagrodzenie;
    }
}
