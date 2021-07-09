"UTF-8"

function escapeHTML(text){
	return text.replace(/&/g, '&amp;')
	           .replace(/</g, '&lt;')
			   .replace(/>/g, '&gt;')
}

function select_text(app_name, bean_name, id){
	var params = { id:id }; 
	var back_f = function(resp){
		if(resp.response.success){
			document.getElementById('selected_text_place').innerHTML = escapeHTML(resp.response['text']);
			document.getElementById('selected_id_place').innerHTML   = escapeHTML(resp.response['id']);
		}
	}
	ajax_exe(app_name, bean_name, 'select', params, back_f);
}

