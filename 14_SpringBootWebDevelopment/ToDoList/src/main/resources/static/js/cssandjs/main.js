$(function() {

    const appendTask = function(data) {
        var taskCode = '<a href="#" class="task-link" data-id="' +
            data.id + '">' + data.taskName + '</a><br>';
        $('#task-list')
            .append('<div>' + taskCode + '</div>');
    };

    //Loading books on load page
    //    $.get('/books/', function(response)
    //    {
    //        for(i in response) {
    //            appendBook(response[i]);
    //        }
    //    });

    //Show adding task form
    $('#show-add-task-form').click(function() {
        $('#task-form').css('display', 'flex');
    });

    //Closing adding task form
    $('#task-form').click(function(event) {
        if (event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting task
    $(document).on('click', '.task-link', function() {
        var link = $(this);
        var taskId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/tasks/' + taskId,
            success: function(response) {
                var code = '<span>Дедлайн: ' + response.deadline + '; Ответственное лицо: ' + response.responsiblePerson + '</span>';
                link.parent().append(code);
            },
            error: function(response) {
                if (response.status == 404) {
                    alert('Задача не найдена!');
                }
            }
        });
        return false;
    });

    //Adding task
    $('#save-task').click(function() {
        var data = $('#task-form form').serialize();
        console.log(data)
        $.ajax({
            method: 'POST',
            url: '/tasks/',
            data: data,
            success: function(response) {
                $('#task-form').css('display', 'none');
                var task = {};
                task.id = response;
                var dataArray = $('#task-form form').serializeArray();
                for (i in dataArray) {
                    task[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendTask(task);
                window.location.reload();
            }
        });
        return false;
    });

    //Updating task
    $(document).on('click', '.edit-task', function() {
        var result = confirm('Вы точно хотите перезаписать данные?');
        if (result) {
            var link = $(this);
            console.log(this);
            console.log(link);
            var taskId = link.data('id');
            $('#task-form').css('display', 'flex');
            var data = $('#task-form form').serialize();
            console.log(data)
            $.ajax({
                method: 'PUT',
                url: '/tasks/' + taskId,
                data: data,
                success: function(response) {
                    $('#task-form').css('display', 'none');
                    var task = {};
                    task.id = taskId;
                    var dataArray = $('#task-form form').serializeArray();
                    for (i in dataArray) {
                        task[dataArray[i]['name']] = dataArray[i]['value'];
                    }
                    //appendTask(task);
                    //window.location.reload();
                }
            });
        }
        return false;
    });

    //Deleting task
    $(document).on('click', '.delete-task', function() {
        var result = confirm('Вы точно хотите удалить запись?');
        if (result) {
            var link = $(this); // this => <button class="delete-task" data-id="19">Удалить</button> id = id дела в списке задач
            var taskId = link.data('id'); // тогда тут будет 19
            console.log(this);
            console.log(link);
            console.log(taskId);

            $.ajax({
                method: 'DELETE',
                url: '/tasks/' + taskId,
                success: function() {
                    $(this).remove();
                    window.location.reload();
                }
            });
        }
        return false;
    });

});