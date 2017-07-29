/**
 * 
 */
var $blogs = $('#blogs');
var $title = $('#title');
var $Message = $('#Message');
var blogTemplate = $('#blog-template').html();

function showBlog(blog) {
	$blogs.append(Mustache.render(blogTemplate, blog));
};

$.ajax({
	type : 'GET',
	url : 'http://localhost:8080/cmadblog/blogsite/blogs/',
	success : function(data) {
		console.log('success', data);
		$.each(data, function(i, blog) {
			console.log('item"', blog);
			showBlog(blog);
		});
	},
	error : function() {
		alert('error loading Blogs');
	}
});

$('#add-blog').on('click', function() {

	var blog = {
			title : $title.val(),
			blogMessage : $Message.val()
	};

	$.ajax({
		type : 'POST',
		  headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
		url : 'http://localhost:8080/cmadblog/blogsite/blogs/',
		data :JSON.stringify( blog),
		dataType : 'json',
		success : function(blog) {
			addOrder(newOrder);
		},
		error : function() {
			alert('error Posting  Blog');
		}
	});
});