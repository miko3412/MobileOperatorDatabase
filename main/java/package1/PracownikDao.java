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
public class PracownikDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Konstruktor z jdbcTemplate
     * @param jdbcTemplate instancja jdbcTemplate
     */
    public PracownikDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pracownik> list(){
        String sql="SELECT * FROM PRACOWNICY";
        List<Pracownik> listPracownicy = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Pracownik.class));
        return listPracownicy;
    }
    
    /* (C)reate */
    public void save(Pracownik pracownik){
    	SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
    	insertActor.withTableName("pracownicy").usingColumns("imie","nazwisko","data_urodzenia","PESEL","plec","nr_telefonu","nr_biura","nr_adresu");
    	
    	BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownik);
    	insertActor.execute(param);
    }
    
    /* (R)ead */
    public Pracownik get(int nr_pracownika){
        Object[] args = {nr_pracownika};
        String sql = "SELECT * FROM PRACOWNICY WHERE NR_PRACOWNIKA = "+args[0];
        Pracownik pracownik = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Pracownik.class));
        return pracownik;
    }
    /* (U)pdate */
    public void update(Pracownik pracownik){
    	String sql = "UPDATE PRACOWNICY SET imie=:imie, nazwisko=:nazwisko, data_urodzenia=:data_urodzenia, pesel=:pesel, plec=:plec, nr_telefonu=:nr_telefonu, nr_biura=:nr_biura, nr_adresu=:nr_adresu WHERE nr_pracownika=:nr_pracownika";
    	BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownik);
    	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
    	
    	template.update(sql, param);
    }
    /* (D)elete */
    public void delete(int id){
    	String sql = "DELETE FROM PRACOWNICY WHERE NR_PRACOWNIKA = ?";
    	jdbcTemplate.update(sql,id);
    }
}
