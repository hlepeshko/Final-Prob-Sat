% Load data from the CSV file
data = csvread('plotted_PSS2.csv', 1, 0);  % Skip the header row

% Extract X and Y values from the data
xvalues = data(:, 1);
yvalues = data(:, 2);

% Salt the X and Y values 
salted_x = xvalues + rand(size(xvalues));  % Salt X values
salted_y = yvalues + rand(size(yvalues));  % Salt Y values

% Create a matrix with salted X and Y values
salted_data = [salted_x, salted_y];

% Save the salted data to a CSV file
csvwrite('salted_PSS2.csv', salted_data);

% Plot the data
plot(salted_x, salted_y);
set(gca, 'LineWidth', 1, 'FontSize', 12);
xlabel('Salted X values');
ylabel('Salted Y values');
title('Salted Plot of PSS2');
grid on;

% Limit the x-axis range to 0 to 12 (to look more like the orignial graph)
xlim([0, 12]);

% Save the grapgh as an image 
saveas(gcf, 'salted_PSS2.png');

% Print the salted X and Y values
disp('Salted X values in the CSV file:');
disp(salted_x);

disp('Salted Y values in the CSV file:');
disp(salted_y);
