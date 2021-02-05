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
public class BiuroDAO {
	@Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Konstruktor z jdbcTemplate
     * @param jdbcTemplate instancja jdbcTemplate
     */
    public BiuroDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Biuro> list(){
        String sql="SELECT * FROM BIURA";
        List<Biuro> listBiura = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Biuro.class));
        return listBiura;
    }
    
    /* (C)reate */
    public void save(Biuro biuro){
    	SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
    	insertActor.withTableName("biura").usingColumns("nazwa","data_zalozenia","nr_adresu");
    	
    	BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(biuro);
    	insertActor.execute(param);
    }
    
    /* (R)ead */
    public Biuro get(int nr_biura){
        Object[] args = {nr_biura};
        String sql = "SELECT * FROM BIURA WHERE NR_BIURA = "+args[0];
        Biuro biuro = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Biuro.class));
        return biuro;
    }
    /* (U)pdate */
    public void update(Biuro biuro){
    	String sql = "UPDATE BIURA SET nazwa=:nazwa, data_zalozenia=:data_zalozenia, nr_adresu=:nr_adresu WHERE nr_biura=:nr_biura";
    	BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(biuro);
    	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
    	
    	template.update(sql, param);
    }
    /* (D)elete */
    public void delete(int id){
    	String sql = "DELETE FROM BIURA WHERE NR_BIURA = ?";
    	jdbcTemplate.update(sql,id);
    }
}
